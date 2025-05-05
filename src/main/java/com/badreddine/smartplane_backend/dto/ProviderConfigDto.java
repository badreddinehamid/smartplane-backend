package com.badreddine.smartplane_backend.dto;

import com.badreddine.smartplane_backend.models.ProviderConfigModel;
import lombok.Data;

import java.util.List;

@Data
public class ProviderConfigDto {
    public ProviderConfigDto(String kind, String apiVersion, List<ProviderConfigModel> items) {
        this.kind = kind;
        this.apiVersion = apiVersion;
        this.items = items;
    }

    private String apiVersion;
    private String kind;
    private List<ProviderConfigModel> items;

    public ProviderConfigDto(){};



}
