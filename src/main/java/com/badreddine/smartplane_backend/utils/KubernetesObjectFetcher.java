package com.badreddine.smartplane_backend.utils;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.ApiextensionsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import org.springframework.stereotype.Component;

@Component
public class KubernetesObjectFetcher {

    private final CustomObjectsApi customObjectsApi;
    private final CoreV1Api api;
    private final ApiextensionsV1Api apiExtensions;


    public KubernetesObjectFetcher(CustomObjectsApi customObjectsApi, CoreV1Api api, ApiextensionsV1Api apiExtensions) {
        this.customObjectsApi = customObjectsApi;
        this.api = api;
        this.apiExtensions = apiExtensions;
    }

    public Object ListKubernetesObjects(String group, String version, String plural) throws Exception {
        try {

            return customObjectsApi.listClusterCustomObject(
                    group, version, plural, null, null, null, null, null, null, null, null, null, null);

        } catch (ApiException e) {
            System.err.println("Error fetching Objects : " + e.getResponseBody());
            e.printStackTrace();
            throw new Exception("Failed to fetch Object  from Kubernetes API", e);
        }

    }
}
