package com.badreddine.smartplane_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProviderDto {
    private String apiVersion;

    private String kind;

    // Optional: Constructor for easy creation
    public ProviderDto() {}

    public ProviderDto(String apiVersion, String kind) {
        this.apiVersion = apiVersion;
        this.kind = kind;
    }
}