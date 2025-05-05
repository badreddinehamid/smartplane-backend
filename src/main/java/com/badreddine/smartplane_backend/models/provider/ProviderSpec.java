package com.badreddine.smartplane_backend.models.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderSpec {

    @JsonProperty("package")
    private String pkg;

    @JsonProperty("controllerConfigRef")
    private ControllerConfigRef controllerConfigRef;


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ControllerConfigRef {
        @JsonProperty("name")
        private String name;

    }
}
