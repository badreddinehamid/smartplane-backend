package com.badreddine.smartplane_backend.services;

import com.badreddine.smartplane_backend.dto.XrdsDto;
import com.badreddine.smartplane_backend.mappers.XrdsMapper;
import com.badreddine.smartplane_backend.models.XrdsModel;
import com.badreddine.smartplane_backend.utils.KubernetesObjectFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XRDsService {


    private final KubernetesObjectFetcher kubernetesObjectFetcher ;


    public XRDsService(KubernetesObjectFetcher kubernetesObjectFetcher) {


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
