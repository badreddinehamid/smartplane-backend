package com.badreddine.smartplane_backend.dto;

import com.badreddine.smartplane_backend.models.ProviderConfigModel;
import lombok.Data;

@Data
public class ProviderConfigDto {
    public ProviderConfigDto(String kind, String apiVersion,ProviderConfigModel.Spec spec) {
        this.kind = kind;
        this.apiVersion = apiVersion;
        this.spec = spec;
    }

    private String apiVersion;
    private String kind;
    private ProviderConfigModel.Spec spec;

    public ProviderConfigDto(){};



}
