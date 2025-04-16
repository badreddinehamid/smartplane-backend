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
public class ClaimService {
    private final CustomObjectsApi customObjectsApi;
    private final CoreV1Api api;
    private final ApiextensionsV1Api apiExtensions;

    public ClaimService(CustomObjectsApi customObjectsApi, CoreV1Api api, ApiextensionsV1Api apiExtensions) {
        this.customObjectsApi = customObjectsApi;
        this.api = api;
        this.apiExtensions = apiExtensions;
    }
    public Object listclaims() throws Exception {
        String group = "apiextensions.crossplane.io";
        String version = "v1";
        String plural = "compositeresourcedefinitions";

        try {
            Object compositeResourceDef =  customObjectsApi.listClusterCustomObject(
                    group, version, plural, null, null, null, null, null, null, null, null, null, null);
            Map<String, Object> responseMap = (Map<String, Object>) compositeResourceDef;
            List<Map<String, Object>> items = (List<Map<String, Object>>) responseMap.get("items");

            List<Map<String, Object>> allClaims = new ArrayList<>();

            // Loop through each XRD to find claim-backed types
            for (Map<String, Object> xrd : items) {
                Map<String, Object> spec = (Map<String, Object>) xrd.get("spec");

                if (spec.containsKey("claimNames")) {
                    Map<String, Object> claimNames = (Map<String, Object>) spec.get("claimNames");
                    String cgroup = (String) spec.get("group");
                    String cplural = (String) claimNames.get("plural");

                    List<Map<String, Object>> versions = (List<Map<String, Object>>) spec.get("versions");
                    String cversion = (String) versions.get(0).get("name"); // first version assumed active

                    System.out.printf("Listing claim: %s.%s (%s)%n", cplural, cgroup, cversion);

                    try {
                        //  List all claim instances of this type
                        Object claims = customObjectsApi.listClusterCustomObject(
                                cgroup, cversion, cplural,
                                null, null, null, null,
                                null, null, null, null, null,null
                        );

                        Map<String, Object> claimsMap = (Map<String, Object>) claims;
                        List<Map<String, Object>> claimItems = (List<Map<String, Object>>) claimsMap.get("items");

                        allClaims.addAll(claimItems);
                    } catch (ApiException ce) {
                        System.err.printf("Failed to list claims for %s.%s: %s%n", cplural, cgroup, ce.getMessage());
                    }
                }
            }

            return allClaims;
        } catch (ApiException e) {
            System.err.println("Error fetching claim: " + e.getResponseBody());
            e.printStackTrace();
            throw new Exception("Failed to fetch claim from Kubernetes API", e);
        }

    }

}
