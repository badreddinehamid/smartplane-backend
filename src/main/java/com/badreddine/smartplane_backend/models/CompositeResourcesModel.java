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
public class CompositeResourcesModel {

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
    public static class CompositeResourceListModel {

        @JsonProperty("apiVersion")
        private String apiVersion;

        @JsonProperty("kind")
        private String kind;

        @JsonProperty("metadata")
        private Map<String, Object> metadata;

        @JsonProperty("items")
        private List<CompositeResourcesModel> items;
    }


    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Metadata {
        private String name;
        private String namespace;
        private String uid;

        @JsonProperty("creationTimestamp")
        private String creationTimestamp;
    }

    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Spec {
        @JsonProperty("compositionSelector")
        private CompositionSelector compositionSelector;

        @JsonProperty("compositionRef")
        private Map<String, Object> compositionRef;

        @JsonProperty("parameters")
        private Map<String, Object> parameters;
    }

    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CompositionSelector {
        @JsonProperty("matchLabels")
        private Map<String, String> matchLabels;
    }

    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Status {
        @JsonProperty("state")
        private String state;
        @JsonProperty("message")
        private String message;
        @JsonProperty("conditions")
        private List<Condition> conditions;
        @JsonProperty("resources")
        private List<ComposedResourceRef> resources;
    }

    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Condition {

        @JsonProperty("type")
        private String type;

        @JsonProperty("status")
        private String status;

        @JsonProperty("reason")
        private String reason;

        @JsonProperty("message")
        private String message;

        @JsonProperty("lastTransitionTime")
        private String lastTransitionTime;
    }


    @Data
    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ComposedResourceRef {

        @JsonProperty("name")
        private String name;

        @JsonProperty("apiVersion")
        private String apiVersion;

        @JsonProperty("kind")
        private String kind;

        @JsonProperty("uid")
        private String uid;
    }
}
