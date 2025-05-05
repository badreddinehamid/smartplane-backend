package com.badreddine.smartplane_backend.models.providerConfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class ProviderConfigStatus {

    @JsonProperty("users")
    private Double users;
}
