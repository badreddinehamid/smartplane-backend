package com.badreddine.smartplane_backend.models.providerConfig;

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
public class ProviderConfigMetadata {

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



    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Component
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

}
