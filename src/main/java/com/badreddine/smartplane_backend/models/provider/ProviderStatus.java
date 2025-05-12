package com.badreddine.smartplane_backend.models.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderStatus {

    @JsonProperty("installed")
    private Installed installed;

    @JsonProperty("conditions")
    private List<Condition> conditions;

    // Getters and Setters
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Installed {

        @JsonProperty("type")
        private String type;

        @JsonProperty("lastTransitionTime")
        private String lastTransitionTime;

        @JsonProperty("reason")
        private String reason;

        @JsonProperty("message")
        private String message;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Condition {

        @JsonProperty("type")
        private String type;

        @JsonProperty("status")
        private String status;

        @JsonProperty("lastTransitionTime")
        private String lastTransitionTime;

    }
}
