package com.badreddine.smartplane_backend.models.providerConfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class ProviderConfigModel {

    @JsonProperty("apiVersion")
    private String apiVersion;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("metadata")
    private ProviderConfigMetadata metadata;

    @JsonProperty("spec")
    private ProviderConfigSpec spec;

    @JsonProperty("status")
    private ProviderConfigStatus status;
}
