package com.badreddine.smartplane_backend.dto;

import com.badreddine.smartplane_backend.models.ProviderModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProviderDto {
    private String apiVersion;

    private String kind;
    private ProviderModel.ProviderMetadata metadata;

    // Optional: Constructor for easy creation
    public ProviderDto() {}

    public ProviderDto(String apiVersion, String kind,ProviderModel.ProviderMetadata metadata) {
        this.apiVersion = apiVersion;
        this.kind = kind;
        this.metadata = metadata;
    }
}