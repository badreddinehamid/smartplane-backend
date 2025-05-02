package com.badreddine.smartplane_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderConfigModel {

    @JsonProperty("apiVersion")
    private String apiVersion;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("metadata")
    private Metadata metadata;

    @JsonProperty("spec")
    private Spec spec;

    @JsonProperty("status")
    private Status status;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {
        @JsonProperty("name")
        private String name;

        @JsonProperty("namespace")
        private String namespace;

        @JsonProperty("labels")
        private Map<String, String> labels;

        @JsonProperty("annotations")
        private Map<String, String> annotations;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Spec {
        @JsonProperty("credentials")
        private Credentials credentials;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Credentials {
        @JsonProperty("source")
        private String source;

        @JsonProperty("secretRef")
        private SecretRef secretRef;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SecretRef {
        @JsonProperty("namespace")
        private String namespace;

        @JsonProperty("name")
        private String name;

        @JsonProperty("key")
        private String key;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Status {
        @JsonProperty("conditions")
        private Condition[] conditions;

        @JsonProperty("users")
        private String[] users;

        @JsonProperty("accountId")
        private String accountId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Condition {
        @JsonProperty("lastTransitionTime")
        private String lastTransitionTime;

        @JsonProperty("reason")
        private String reason;

        @JsonProperty("status")
        private String status;

        @JsonProperty("type")
        private String type;
    }
}
