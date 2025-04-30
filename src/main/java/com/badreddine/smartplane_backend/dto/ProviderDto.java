package com.badreddine.smartplane_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDto {
    @JsonProperty("apiVersion")
    private String apiVersion;

    @JsonProperty("kind")
    private String kind;

    // Optional: Constructor for easy creation
    public ProviderDto() {}

    public ProviderDto(String apiVersion, String kind) {
        this.apiVersion = apiVersion;
        this.kind = kind;
    }
}