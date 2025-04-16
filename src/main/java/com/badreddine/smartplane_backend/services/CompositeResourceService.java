package com.badreddine.smartplane_backend.services;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.ApiextensionsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CompositeResourceService {

    private final CustomObjectsApi customObjectsApi;
    private final CoreV1Api api;
    private final ApiextensionsV1Api apiExtensions;


    public CompositeResourceService(CustomObjectsApi customObjectsApi, CoreV1Api api, ApiextensionsV1Api apiExtensions) {
        this.customObjectsApi = customObjectsApi;
        this.api = api;
        this.apiExtensions = apiExtensions;
    }

    public Object listCompositeResources() throws Exception {
        String xrdGroup = "apiextensions.crossplane.io";
        String xrdVersion = "v1";
        String xrdPlural = "compositeresourcedefinitions";

        try {
            Object xrdResponse = customObjectsApi.listClusterCustomObject(
                    xrdGroup, xrdVersion, xrdPlural,
                    null, null, null, null,
                    null, null, null, null, null,null
            );

            Map<String, Object> responseMap = (Map<String, Object>) xrdResponse;
            List<Map<String, Object>> xrdItems = (List<Map<String, Object>>) responseMap.get("items");

            List<Map<String, Object>> allXRs = new ArrayList<>();

            for (Map<String, Object> xrd : xrdItems) {
                Map<String, Object> spec = (Map<String, Object>) xrd.get("spec");

                // Extract composite resource info (not the claimNames)
                Map<String, Object> names = (Map<String, Object>) spec.get("names");
                String group = (String) spec.get("group");
                String plural = (String) names.get("plural");

                List<Map<String, Object>> versions = (List<Map<String, Object>>) spec.get("versions");
                String version = (String) versions.get(0).get("name"); // pick first version

                System.out.printf("Fetching composite: %s.%s (%s)%n", plural, group, version);

                try {
                    Object xrList = customObjectsApi.listClusterCustomObject(
                            group, version, plural,
                            null, null, null, null,
                            null, null, null, null, null,null
                    );

                    Map<String, Object> xrMap = (Map<String, Object>) xrList;
                    List<Map<String, Object>> items = (List<Map<String, Object>>) xrMap.get("items");

                    allXRs.addAll(items);
                } catch (ApiException ce) {
                    System.err.printf("Failed to list composites for %s.%s: %s%n", plural, group, ce.getMessage());
                }
            }

            return allXRs;

        } catch (ApiException e) {
            System.err.println("Error fetching XRDs: " + e.getResponseBody());
            throw new Exception("Failed to fetch CompositeResourceDefinitions", e);
        }
    }
}
