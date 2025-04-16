package com.badreddine.smartplane_backend.services;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.ApiextensionsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import org.springframework.stereotype.Service;

@Service
public class XRDsService {
    private final CustomObjectsApi customObjectsApi;
    private final CoreV1Api api;
    private final ApiextensionsV1Api apiExtensions;

    public XRDsService(CustomObjectsApi customObjectsApi, CoreV1Api api, ApiextensionsV1Api apiExtensions) {
        this.customObjectsApi = customObjectsApi;
        this.api = api;
        this.apiExtensions = apiExtensions;
    }
        public Object listXrds() throws Exception {
            String group = "apiextensions.crossplane.io";
            String version = "v1";
            String plural = "compositeresourcedefinitions";

            try {
                return customObjectsApi.listClusterCustomObject(
                        group, version, plural, null, null, null, null, null, null, null, null, null, null);
            } catch (ApiException e) {
                System.err.println("Error fetching compositions: " + e.getResponseBody());
                e.printStackTrace();
                throw new Exception("Failed to fetch compositions from Kubernetes API", e);
            }
        }

}
