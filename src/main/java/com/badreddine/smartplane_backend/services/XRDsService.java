package com.badreddine.smartplane_backend.services;

import com.badreddine.smartplane_backend.dto.XrdsDto;
import com.badreddine.smartplane_backend.mappers.ProviderMapper;
import com.badreddine.smartplane_backend.mappers.XrdsMapper;
import com.badreddine.smartplane_backend.models.XrdsModel;
import com.badreddine.smartplane_backend.models.provider.ProviderListModel;
import com.badreddine.smartplane_backend.utils.KubernetesObjectFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.ApiextensionsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class XRDsService {


    private final CustomObjectsApi customObjectsApi;
    private final CoreV1Api api;
    private final ApiextensionsV1Api apiExtensions;
    private final KubernetesObjectFetcher kubernetesObjectFetcher ;


    public XRDsService(CustomObjectsApi customObjectsApi, CoreV1Api api, ApiextensionsV1Api apiExtensions, KubernetesObjectFetcher kubernetesObjectFetcher) {
        this.customObjectsApi = customObjectsApi;
        this.api = api;
        this.apiExtensions = apiExtensions;

        this.kubernetesObjectFetcher = kubernetesObjectFetcher;
    }
        public List<XrdsDto> listXrds() throws Exception {
            String group = "apiextensions.crossplane.io";
            String version = "v1";
            String plural = "compositeresourcedefinitions";

            try {
                Object rawResponse =  kubernetesObjectFetcher.ListKubernetesObjects(group,version,plural);

                ObjectMapper mapper = new ObjectMapper();
                XrdsModel.XrdsList xrds = mapper.convertValue(rawResponse, XrdsModel.XrdsList.class);
                return XrdsMapper.INSTANCE.xrdsListToDtoList(xrds.getItems());

            } catch (ApiException e) {
                System.err.println("Error fetching compositions: " + e.getResponseBody());
                e.printStackTrace();
                throw new Exception("Failed to fetch compositions from Kubernetes API", e);
            }
        }

}
