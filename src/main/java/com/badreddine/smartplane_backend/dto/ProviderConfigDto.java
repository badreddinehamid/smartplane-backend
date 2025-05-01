package com.badreddine.smartplane_backend.dto;

import lombok.Data;

@Data
public class ProviderConfigDto {
    public ProviderConfigDto(String kind, String apiVersion) {
        this.kind = kind;
        this.apiVersion = apiVersion;
    }

    private String apiVersion;
    private String kind;

    public ProviderConfigDto(){};



}
