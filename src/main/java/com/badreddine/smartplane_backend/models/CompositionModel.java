package com.badreddine.smartplane_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompositionModel {

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
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
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
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Spec {
        @JsonProperty("compositeTypeRef")
        private CompositeTypeRef compositeTypeRef;

        @JsonProperty("resources")
        private List<Resource> resources;

        @JsonProperty("patchSets")
        private List<PatchSet> patchSets;

        @JsonProperty("writeConnectionSecretsToNamespace")
        private String writeConnectionSecretsToNamespace;
    }

    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CompositeTypeRef {
        @JsonProperty("apiVersion")
        private String apiVersion;

        @JsonProperty("kind")
        private String kind;
    }

    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Resource {
        @JsonProperty("name")
        private String name;

        @JsonProperty("base")
        private Map<String, Object> base;

        @JsonProperty("patches")
        private List<Patch> patches;

        @JsonProperty("connectionDetails")
        private List<Map<String, Object>> connectionDetails;

        @JsonProperty("readinessChecks")
        private List<Map<String, Object>> readinessChecks;
    }

    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Patch {
        @JsonProperty("type")
        private String type;

        @JsonProperty("fromFieldPath")
        private String fromFieldPath;

        @JsonProperty("toFieldPath")
        private String toFieldPath;

        @JsonProperty("policy")
        private Map<String, Object> policy;

        @JsonProperty("transforms")
        private List<Map<String, Object>> transforms;
    }

    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PatchSet {
        @JsonProperty("name")
        private String name;

        @JsonProperty("patches")
        private List<Patch> patches;
    }

    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Status {
        @JsonProperty("conditions")
        private List<Map<String, Object>> conditions;
    }

    // 👇 Inner class for the list of compositions
    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CompositionList {
        @JsonProperty("apiVersion")
        private String apiVersion;

        @JsonProperty("kind")
        private String kind;

        @JsonProperty("items")
        private List<CompositionModel> items;
    }
}
