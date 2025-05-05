package com.badreddine.smartplane_backend.models.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class ProviderModel {
    @JsonProperty("apiVersion")
    private String apiVersion;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("metadata")
    private ProviderMetadata metadata;


    @JsonProperty("spec")
    private ProviderSpec spec;

    @JsonProperty("status")
    private ProviderStatus status;


}
