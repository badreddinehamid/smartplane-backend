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
public class ProviderModel {
    // Top-level provider fields
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

    // Metadata inner class
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProviderMetadata {
        @JsonProperty("annotations")
        private Map<String, String> annotations;

        @JsonProperty("creationTimestamp")
        private Date creationTimestamp;

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

    // ManagedField inner class
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

    // Spec inner class
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProviderSpec {
        @JsonProperty("ignoreCrossplaneConstraints")
        private Boolean ignoreCrossplaneConstraints;

        @JsonProperty("package")
        private String packageName;

        @JsonProperty("packagePullPolicy")
        private String packagePullPolicy;

        @JsonProperty("revisionActivationPolicy")
        private String revisionActivationPolicy;

        @JsonProperty("revisionHistoryLimit")
        private Double revisionHistoryLimit;

        @JsonProperty("runtimeConfigRef")
        private RuntimeConfigRef runtimeConfigRef;

        @JsonProperty("skipDependencyResolution")
        private Boolean skipDependencyResolution;
    }

    // RuntimeConfigRef inner class
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RuntimeConfigRef {
        @JsonProperty("apiVersion")
        private String apiVersion;

        @JsonProperty("kind")
        private String kind;

        @JsonProperty("name")
        private String name;
    }

    // Status inner class
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProviderStatus {
        @JsonProperty("conditions")
        private List<ProviderCondition> conditions;

        @JsonProperty("currentIdentifier")
        private String currentIdentifier;

        @JsonProperty("currentRevision")
        private String currentRevision;
    }

    // Condition inner class
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProviderCondition {
        @JsonProperty("lastTransitionTime")
        private Date lastTransitionTime;

        @JsonProperty("reason")
        private String reason;

        @JsonProperty("status")
        private String status;

        @JsonProperty("type")
        private String type;
    }

    // ProviderList inner class (for the collection response)
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProviderList {
        @JsonProperty("apiVersion")
        private String apiVersion;

        @JsonProperty("items")
        private List<ProviderModel> items;

        @JsonProperty("kind")
        private String kind;

        @JsonProperty("metadata")
        private ProviderListMetadata metadata;
    }

    // ListMetadata inner class
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProviderListMetadata {
        @JsonProperty("continue")
        private String continueToken;

        @JsonProperty("resourceVersion")
        private String resourceVersion;
    }
}