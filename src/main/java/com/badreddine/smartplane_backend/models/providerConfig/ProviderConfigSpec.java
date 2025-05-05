package com.badreddine.smartplane_backend.models.providerConfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class ProviderConfigSpec {

    @JsonProperty("credentials")
    private Credentials credentials;

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
    @Component
    public static class SecretRef {

        @JsonProperty("key")
        private String key;

        @JsonProperty("name")
        private String name;

        @JsonProperty("namespace")
        private String namespace;
    }
}
