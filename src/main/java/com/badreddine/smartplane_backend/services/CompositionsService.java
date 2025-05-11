package com.badreddine.smartplane_backend.services;

import com.badreddine.smartplane_backend.dto.CompositionDto;
import com.badreddine.smartplane_backend.mappers.CompositionMapper;
import com.badreddine.smartplane_backend.mappers.ProviderMapper;
import com.badreddine.smartplane_backend.models.CompositionModel;
import com.badreddine.smartplane_backend.models.provider.ProviderListModel;
import com.badreddine.smartplane_backend.utils.KubernetesObjectFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.ApiextensionsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositionsService {
    private final CustomObjectsApi customObjectsApi;
    private final CoreV1Api api;
    private final ApiextensionsV1Api apiExtensions;
    private final KubernetesObjectFetcher kubernetesObjectFetcher ;


    public CompositionsService(CustomObjectsApi customObjectsApi, CoreV1Api api, ApiextensionsV1Api apiExtensions, KubernetesObjectFetcher kubernetesObjectFetcher) {
        this.customObjectsApi = customObjectsApi;
        this.api = api;
        this.apiExtensions = apiExtensions;
        this.kubernetesObjectFetcher = kubernetesObjectFetcher;
    }

    public List<CompositionDto> listCompositions() throws Exception {
        String group = "apiextensions.crossplane.io";
        String version = "v1";
        String plural = "compositions";

        Object rawResponse = kubernetesObjectFetcher.ListKubernetesObjects(group,version,plural);

        ObjectMapper mapper = new ObjectMapper();
        CompositionModel.CompositionList compositions = mapper.convertValue(rawResponse, CompositionModel.CompositionList.class);
        return CompositionMapper.INSTANCE.compositionListToCompositionDto(compositions.getItems());

    }
}
