package com.badreddine.smartplane_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class XrdsModel {

    @JsonProperty("apiVersion")
    private String apiVersion;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("metadata")
    private Metadata metadata;

    @JsonProperty("spec")
    private Spec spec;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Component
    public static class XrdsList{
        @JsonProperty("apiVersion")
        private String apiVersion;

        @JsonProperty("kind")
        private String kind;

        @JsonProperty("items")
        private List<XrdsModel> items;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Component
    public static class Metadata {
        @JsonProperty("name")
        private String name;

        @JsonProperty("uid")
        private String uid;

        @JsonProperty("resourceVersion")
        private String resourceVersion;

        @JsonProperty("labels")
        private Map<String, String> labels;

        @JsonProperty("annotations")
        private Map<String, String> annotations;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Component
    public static class Spec {
        @JsonProperty("group")
        private String group;

        @JsonProperty("names")
        private Names names;

        @JsonProperty("claimNames")
        private ClaimNames claimNames;

        @JsonProperty("connectionSecretKeys")
        private List<String> connectionSecretKeys;

        @JsonProperty("versions")
        private List<Version> versions;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Component
    public static class Names {
        @JsonProperty("kind")
        private String kind;

        @JsonProperty("plural")
        private String plural;

        @JsonProperty("singular")
        private String singular;

        @JsonProperty("listKind")
        private String listKind;

        @JsonProperty("shortNames")
        private List<String> shortNames;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Component
    public static class ClaimNames {
        @JsonProperty("kind")
        private String kind;

        @JsonProperty("plural")
        private String plural;

        @JsonProperty("singular")
        private String singular;

        @JsonProperty("listKind")
        private String listKind;

        @JsonProperty("shortNames")
        private List<String> shortNames;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Component
    public static class Version {
        @JsonProperty("name")
        private String name;

        @JsonProperty("served")
        private boolean served;

        @JsonProperty("referenceable")
        private boolean referenceable;

        @JsonProperty("schema")
        private Schema schema;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Component
    public static class Schema {
        @JsonProperty("openAPIV3Schema")
        private OpenAPIV3Schema openAPIV3Schema;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Component
    public static class OpenAPIV3Schema {
        @JsonProperty("type")
        private String type;

        @JsonProperty("properties")
        private Map<String, Object> properties;
    }
}
