package com.badreddine.smartplane_backend.services;
import com.badreddine.smartplane_backend.dto.ProviderDto;
import com.badreddine.smartplane_backend.dto.ProviderFilterDto;
import com.badreddine.smartplane_backend.mappers.ProviderMapper;
import com.badreddine.smartplane_backend.models.provider.ProviderListModel;
import com.badreddine.smartplane_backend.models.provider.ProviderModel;
import com.badreddine.smartplane_backend.utils.KubernetesObjectFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.ApiextensionsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;

import io.kubernetes.client.openapi.models.V1Secret;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service

public class ProvidersService {

    private final CustomObjectsApi customObjectsApi;
    private final CoreV1Api api;
    private final ApiextensionsV1Api apiExtensions;
    private final KubernetesObjectFetcher kubernetesObjectFetcher ;

    public ProvidersService(CustomObjectsApi customObjectsApi, CoreV1Api api, ApiextensionsV1Api apiExtensions, KubernetesObjectFetcher kubernetesObjectFetcher) {
        this.customObjectsApi = customObjectsApi;
        this.api = api;
        this.apiExtensions = apiExtensions;
        this.kubernetesObjectFetcher = kubernetesObjectFetcher;
    }

    public List<ProviderDto> listProviders() throws Exception {
        String group = "pkg.crossplane.io";
        String version = "v1";
        String plural = "providers";

        Object rawResponse = kubernetesObjectFetcher.ListKubernetesObjects(group,version,plural);

            ObjectMapper mapper = new ObjectMapper();
            ProviderListModel providers = mapper.convertValue(rawResponse, ProviderListModel.class);
            return ProviderMapper.INSTANCE.providerListToProviderDtoList(providers.getItems());


    }

    public List<ProviderDto> listFiltredProviders(ProviderFilterDto filterDto) throws Exception {

        String group = "pkg.crossplane.io";
        String version = "v1";
        String plural = "providers";

        Object rawResponse = kubernetesObjectFetcher.ListKubernetesObjects(group,version,plural);

        ObjectMapper mapper = new ObjectMapper();
        ProviderListModel providers = mapper.convertValue(rawResponse, ProviderListModel.class);

        Predicate<ProviderModel> filter = buildPredicate(filterDto);
        List<ProviderModel> filteredProviders =providers.getItems().stream().filter(filter).toList();

        return ProviderMapper.INSTANCE.providerListToProviderDtoList(filteredProviders);
    }

    private Predicate<ProviderModel> buildPredicate(ProviderFilterDto dto) {
        List<Predicate<ProviderModel>> predicates = new ArrayList<>();

        if (dto.getName() != null)
            predicates.add(p -> p.getMetadata() != null && dto.getName().equals(p.getMetadata().getName()));

        if (dto.getNamespace() != null)
            predicates.add(p -> p.getMetadata() != null && dto.getNamespace().equals(p.getMetadata().getNamespace()));

        if (dto.getPackageName() != null)
            predicates.add(p -> p.getSpec() != null &&
                    p.getSpec().getPkg() != null &&
                    p.getSpec().getPkg().contains(dto.getPackageName()));

        if (dto.getHealthy() != null)
            predicates.add(p -> hasCondition(p, "Healthy", dto.getHealthy() ? "True" : "False"));

        if (dto.getInstalled() != null)
            predicates.add(p -> hasCondition(p, "Installed", dto.getInstalled() ? "True" : "False"));

        if (dto.getLabels() != null && !dto.getLabels().isEmpty())
            predicates.add(p -> {
                if (p.getMetadata() == null || p.getMetadata().getLabels() == null) return false;
                return dto.getLabels().entrySet().stream()
                        .allMatch(e -> e.getValue().equals(p.getMetadata().getLabels().get(e.getKey())));
            });



        return predicates.stream().reduce(x -> true, Predicate::and);
    }

    private boolean hasCondition(ProviderModel provider, String type, String expectedStatus) {
        if (provider.getStatus() == null || provider.getStatus().getConditions() == null) return false;

        return provider.getStatus().getConditions().stream()
                .anyMatch(cond -> type.equals(cond.getType()) && expectedStatus.equalsIgnoreCase(cond.getStatus()));

    }




    public Map<String, Object> listProviderconfigsets() throws Exception {
        String group = "aws.upbound.io";
        String version = "v1beta1";
        String plural = "providerconfigs";

        Map<String, Object> rawResponse = (Map<String, Object>) kubernetesObjectFetcher.ListKubernetesObjects(group, version, plural);
        List<Map<String, Object>> items = (List<Map<String, Object>>) rawResponse.get("items");

        ObjectMapper mapper = new ObjectMapper();

//        ProviderConfigModel.ProviderConfigList providersconfigs = mapper.convertValue(rawResponse, ProviderConfigModel.ProviderConfigList.class);



        return rawResponse;
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



        }







