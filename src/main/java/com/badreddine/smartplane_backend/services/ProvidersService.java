package com.badreddine.smartplane_backend.services;
import com.badreddine.smartplane_backend.dto.ProviderDto;
import com.badreddine.smartplane_backend.mappers.ProviderMapper;
import com.badreddine.smartplane_backend.models.ProviderModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.ApiextensionsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import io.kubernetes.client.openapi.models.V1CustomResourceDefinition;
import io.kubernetes.client.openapi.models.V1CustomResourceDefinitionList;
import io.kubernetes.client.openapi.models.V1Secret;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProvidersService {

    private final CustomObjectsApi customObjectsApi;
    private final CoreV1Api api;
    private final ApiextensionsV1Api apiExtensions;

    private final ProviderMapper providerMapper;

    public ProvidersService(CustomObjectsApi customObjectsApi, CoreV1Api api, ApiextensionsV1Api apiExtensions, ProviderMapper providerMapper) {
        this.customObjectsApi = customObjectsApi;
        this.api = api;
        this.apiExtensions = apiExtensions;
        this.providerMapper = providerMapper;
    }

    public ProviderDto listProviders() throws Exception {
        String group = "pkg.crossplane.io";
        String version = "v1";
        String plural = "providers";

        try {
            Object rawResponse = customObjectsApi.listClusterCustomObject(
                    group, version, plural, null, null, null, null, null, null, null, null, null, null);

            // Convert the raw Object to your ProviderModel.ProviderList
            ObjectMapper mapper = new ObjectMapper();
            ProviderModel.ProviderList providers = mapper.convertValue(rawResponse, ProviderModel.ProviderList.class);


            return providerMapper.ProviderListToProviderDto(providers);
        } catch (ApiException e) {
            System.err.println("Error fetching providers: " + e.getResponseBody());
            e.printStackTrace();
            throw new Exception("Failed to fetch providers from Kubernetes API", e);
        }
    }

    public Object listProviderconfigsets() throws Exception {
        String group = "aws.upbound.io";
        String version = "v1beta1";
        String plural = "providerconfigs";

        try {
            Map<String, Object> providerConfigs = (Map<String, Object>) customObjectsApi.listClusterCustomObject(
                    group, version, plural, null, null, null, null, null, null, null, null, null, null);

            List<Map<String, Object>> items = (List<Map<String, Object>>) providerConfigs.get("items");
            Map<String, Object> providerConfigsWithSecretInfo = new HashMap<>();

            for (Map<String, Object> item : items) {
                Map<String, Object> metadata = (Map<String, Object>) item.get("metadata");
                Map<String, Object> spec = (Map<String, Object>) item.get("spec");

                if (spec.containsKey("credentials")) {
                    Map<String, Object> credentials = (Map<String, Object>) spec.get("credentials");

                    if ("Secret".equals(credentials.get("source"))) {
                        Map<String, Object> secretRef = (Map<String, Object>) credentials.get("secretRef");

                        if (secretRef != null) {
                            String secretName = (String) secretRef.get("name");
                            String secretNamespace = (String) secretRef.get("namespace");
                            String secretKey = (String) secretRef.get("key");

                            Map<String, Object> secretInfo = getSecretInfo(secretNamespace, secretName, secretKey);

                            String metadataName = metadata.get("name") != null ? metadata.get("name").toString() : "unknown";

                            Map<String, Object> enrichedProviderConfig = new HashMap<>(item);
                            enrichedProviderConfig.put("secretInfo", secretInfo);
                            providerConfigsWithSecretInfo.put(metadataName, enrichedProviderConfig);
                        }
                    }
                }
            }

            return providerConfigsWithSecretInfo;
        } catch (ApiException e) {
            System.err.println("Error fetching providerconfigs: " + e.getResponseBody());
            throw new Exception("Failed to fetch providerconfigs from Kubernetes API", e);
        }
    }



    private Map<String, Object> getSecretInfo(String namespace, String secretName, String secretKey) throws Exception {
        try {
            V1Secret secret = api.readNamespacedSecret(secretName, namespace, null);

            Map<String, Object> secretInfo = new HashMap<>();
            secretInfo.put("name", secretName);
            secretInfo.put("namespace", namespace);
            secretInfo.put("type", secret.getType());

            if (secret.getData() != null && secret.getData().containsKey(secretKey)) {
                int byteSize = secret.getData().get(secretKey).length;
                secretInfo.put("size_bytes", byteSize);
            } else {
                secretInfo.put("size_bytes", 0);
            }

            return secretInfo;
        } catch (ApiException e) {
            System.err.println("Error fetching secret: " + e.getResponseBody());
            throw new Exception("Failed to fetch secret from Kubernetes API", e);
        }
    }

    public Object getProviderStatus(String providerName) throws Exception {
        String group = "pkg.crossplane.io";
        String version = "v1";
        String plural = "providers";

        try {
            Map<String, Object> response = (Map<String, Object>) customObjectsApi.listClusterCustomObject(
                    group, version, plural, null, null, null, null, null, null, null, null, null, null);

            List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");

            for (Map<String, Object> item : items) {
                Map<String, Object> metadata = (Map<String, Object>) item.get("metadata");
                if (metadata.get("name").equals(providerName)) {
                    Map<String, Object> status = (Map<String, Object>) item.get("status");
                    List<Map<String, Object>> conditions = (List<Map<String, Object>>) status.get("conditions");

                    String healthStatus = "Unknown";
                    String installedStatus = "Unknown";

                    for (Map<String, Object> condition : conditions) {
                        if ("Healthy".equals(condition.get("type"))) {
                            healthStatus = (String) condition.get("status");
                        }
                        if ("Installed".equals(condition.get("type"))) {
                            installedStatus = (String) condition.get("status");
                        }
                    }

                    return Map.of(
                            "provider", providerName,
                            "healthy", healthStatus,
                            "installed", installedStatus
                    );
                }
            }

            return Map.of("error", "Provider not found");
        } catch (ApiException e) {
            throw new Exception("Failed to fetch provider status from Kubernetes API", e);
        }
    }

    public Object getProviderEvents(String providerName, String namespace) throws Exception {
        try {
            var events = api.listNamespacedEvent(namespace, null, null, null, null, null, null, null, null, null, null);

            List<Map<String, Object>> eventList = new ArrayList<>();

            events.getItems().forEach(event -> {
                if (event.getInvolvedObject().getName().equals(providerName)) {
                    Map<String, Object> eventDetails = new HashMap<>();
                    eventDetails.put("type", event.getType());
                    eventDetails.put("reason", event.getReason());
                    eventDetails.put("message", event.getMessage());
                    eventDetails.put("timestamp", event.getLastTimestamp());

                    eventList.add(eventDetails);
                }
            });

            return eventList.isEmpty() ? events : eventList;

        } catch (ApiException e) {
            throw new Exception("Failed to fetch events for provider " + providerName, e);
        }
    }

    public Object listProviderApiResources(String providerGroup) throws Exception {
        try {
            V1CustomResourceDefinitionList crds = apiExtensions.listCustomResourceDefinition(
                    null, null, null, null, null, null, null, null, null, null);
            List<Map<String, Object>> relatedResources = new ArrayList<>();

            for (V1CustomResourceDefinition crd : crds.getItems()) {
                String crdGroup = crd.getSpec().getGroup();
                if (crdGroup.contains(providerGroup)) {
                    Map<String, Object> resourceInfo = Map.of(
                            "name", crd.getMetadata().getName(),
                            "group", crdGroup,
                            "kind", crd.getSpec().getNames().getKind(),
                            "version", crd.getSpec().getVersions().stream().map(v -> v.getName()).collect(Collectors.toList()),
                            "namespaced", "Namespaced".equals(crd.getSpec().getScope())
                    );
                    relatedResources.add(resourceInfo);
                }
            }
            return relatedResources.isEmpty() ? Map.of("message", "No API resources found for provider: " + providerGroup) : relatedResources;
        } catch (ApiException e) {
            throw new Exception("Failed to fetch API resources for provider " + providerGroup, e);
        }
    }

}




