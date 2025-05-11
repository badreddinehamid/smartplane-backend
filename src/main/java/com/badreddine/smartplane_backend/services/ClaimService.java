package com.badreddine.smartplane_backend.services;

import com.badreddine.smartplane_backend.dto.ClaimDto;
import com.badreddine.smartplane_backend.mappers.ClaimMapper;
import com.badreddine.smartplane_backend.models.ClaimsModel;
import com.badreddine.smartplane_backend.models.XrdsModel;
import com.badreddine.smartplane_backend.utils.KubernetesObjectFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.apis.ApiextensionsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimService {
    private final CustomObjectsApi customObjectsApi;
    private final CoreV1Api api;
    private final ApiextensionsV1Api apiExtensions;
    private final KubernetesObjectFetcher kubernetesObjectFetcher;
    ObjectMapper objectMapper = new ObjectMapper();

    public ClaimService(CustomObjectsApi customObjectsApi, CoreV1Api api, ApiextensionsV1Api apiExtensions, KubernetesObjectFetcher kubernetesObjectFetcher) {
        this.customObjectsApi = customObjectsApi;
        this.api = api;
        this.apiExtensions = apiExtensions;
        this.kubernetesObjectFetcher = kubernetesObjectFetcher;

    }
    public List<ClaimDto> listclaims() throws Exception {
        String xrdGroup = "apiextensions.crossplane.io";
        String xrdVersion = "v1";
        String xrdPlural = "compositeresourcedefinitions";

        List<ClaimsModel> claimsList = new ArrayList<>();

        Object xrdsRawResponse =  kubernetesObjectFetcher.ListKubernetesObjects(xrdGroup,xrdVersion,xrdPlural);

        XrdsModel.XrdsList xrds = objectMapper.convertValue(xrdsRawResponse, XrdsModel.XrdsList.class);
        List<XrdsModel> xrdsItems = xrds.getItems();

        for(XrdsModel xrd :xrdsItems){
            if (xrd.getSpec().getClaimNames() != null){
                String group = xrd.getSpec().getGroup();
                String plural = xrd.getSpec().getClaimNames().getPlural();
                String version = xrd.getSpec().getVersions().getFirst().getName();


                Object rawResponse = kubernetesObjectFetcher.ListKubernetesObjects(group,version,plural);

                ClaimsModel.ClaimsListModel claim =objectMapper.convertValue(rawResponse, ClaimsModel.ClaimsListModel.class);
                claimsList.addAll(claim.getItems());

            }
        }

        return ClaimMapper.INSTANCE.claimListToClaimDto(claimsList);
    }

}
