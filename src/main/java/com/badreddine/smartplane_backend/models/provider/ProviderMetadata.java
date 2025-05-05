package com.badreddine.smartplane_backend.models.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderMetadata {

    @JsonProperty("name")
    private String name;

    @JsonProperty("namespace")
    private String namespace;

    @JsonProperty("creationTimestamp")
    private String creationTimestamp;

    @JsonProperty("labels")
    private Map<String, String> labels;
}
