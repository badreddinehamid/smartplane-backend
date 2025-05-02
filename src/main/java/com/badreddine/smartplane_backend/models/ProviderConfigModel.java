package com.badreddine.smartplane_backend.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        @JsonProperty("annotations")
        private Map<String, String> annotations;

        @JsonProperty("creationTimestamp")
        private Date creationTimestamp;

        @JsonProperty("finalizers")
        private List<String> finalizers;

        @JsonProperty("generation")
        private Double generation;

        @JsonProperty("managedFields")
        private List<ManagedField> managedFields;

        @JsonProperty("name")
        private String name;

        @JsonProperty("resourceVersion")
        private String resourceVersion;

        @JsonProperty("uid")
        private String uid;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ManagedField {
        @JsonProperty("apiVersion")
        private String apiVersion;

        @JsonProperty("fieldsType")
        private String fieldsType;

        @JsonProperty("fieldsV1")
        private Map<String, Object> fieldsV1;

        @JsonProperty("manager")
        private String manager;

        @JsonProperty("operation")
        private String operation;

        @JsonProperty("time")
        private Date time;

        @JsonProperty("subresource")
        private String subresource;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Spec {
        @JsonProperty("credentials")
        private Credentials credentials;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Credentials {
        @JsonProperty("secretRef")
        private SecretRef secretRef;

        @JsonProperty("source")
        private String source;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SecretRef {
        @JsonProperty("key")
        private String key;

        @JsonProperty("name")
        private String name;

        @JsonProperty("namespace")
        private String namespace;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Status {
        @JsonProperty("users")
        private Double users;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProviderConfigList {
        @JsonProperty("apiVersion")
        private String apiVersion;

        @JsonProperty("items")
        private List<ProviderConfigModel> items;

        @JsonProperty("kind")
        private String kind;

        @JsonProperty("metadata")
        private ProviderListMetadata metadata;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProviderListMetadata {
        @JsonProperty("continue")
        private String continueToken;

        @JsonProperty("resourceVersion")
        private String resourceVersion;
    }
}
