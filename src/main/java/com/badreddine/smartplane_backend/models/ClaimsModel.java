package com.badreddine.smartplane_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimsModel {

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
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Metadata {
        @JsonProperty("name")
        private String name;

        @JsonProperty("namespace")
        private String namespace;

        @JsonProperty("uid")
        private String uid;

        @JsonProperty("creationTimestamp")
        private String creationTimestamp;

        @JsonProperty("annotations")
        private Map<String, String> annotations;

        @JsonProperty("labels")
        private Map<String, String> labels;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Spec {
        @JsonProperty("parameters")
        private Map<String, Object> parameters;

        @JsonProperty("compositionSelector")
        private Map<String, Object> compositionSelector;

        @JsonProperty("writeConnectionSecretToRef")
        private WriteConnectionSecretToRef writeConnectionSecretToRef;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class WriteConnectionSecretToRef {
            @JsonProperty("name")
            private String name;

            @JsonProperty("namespace")
            private String namespace;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Status {
        @JsonProperty("conditions")
        private List<Condition> conditions;

        @JsonProperty("connection")
        private Connection connection;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Condition {
            @JsonProperty("type")
            private String type;

            @JsonProperty("status")
            private String status;

            @JsonProperty("lastTransitionTime")
            private String lastTransitionTime;

            @JsonProperty("reason")
            private String reason;

            @JsonProperty("message")
            private String message;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Connection {
            @JsonProperty("secretRef")
            private SecretRef secretRef;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class SecretRef {
                @JsonProperty("name")
                private String name;
            }
        }



    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClaimsListModel {

        @JsonProperty("apiVersion")
        private String apiVersion;

        @JsonProperty("kind")
        private String kind;

        @JsonProperty("items")
        private List<ClaimsModel> items;
    }
}
