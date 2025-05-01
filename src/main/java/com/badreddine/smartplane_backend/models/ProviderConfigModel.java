package com.badreddine.smartplane_backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderConfigModel {
    private SecretInfo secretInfo;
    private Metadata metadata;
    private String apiVersion;
    private String kind;
    private Spec spec;
    private Status status;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SecretInfo {
        private int sizeBytes;
        private String name;
        private String namespace;
        private String type;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {
        private Annotations annotations;
        private String creationTimestamp;
        private List<String> finalizers;
        private double generation;
        private List<ManagedField> managedFields;
        private String name;
        private String resourceVersion;
        private String uid;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Annotations {
            @JsonProperty("kubectl.kubernetes.io/last-applied-configuration")
            private String lastAppliedConfiguration;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ManagedField {
            private String apiVersion;
            private String fieldsType;
            private FieldsV1 fieldsV1;
            private String manager;
            private String operation;
            private String time;
            private String subresource;

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class FieldsV1 {
                @JsonProperty("f:metadata")
                private FMetadata fMetadata;
                @JsonProperty("f:spec")
                private FSpec fSpec;
                @JsonProperty("f:status")
                private FStatus fStatus;

                @Data
                @NoArgsConstructor
                @AllArgsConstructor
                public static class FMetadata {
                    @JsonProperty("f:annotations")
                    private FAnnotations fAnnotations;
                    @JsonProperty("f:finalizers")
                    private FFinalizers fFinalizers;

                    @Data
                    @NoArgsConstructor
                    @AllArgsConstructor
                    public static class FAnnotations {
                        @JsonProperty(".")
                        private Empty empty;
                        @JsonProperty("f:kubectl.kubernetes.io/last-applied-configuration")
                        private Empty lastAppliedConfig;
                    }

                    @Data
                    @NoArgsConstructor
                    @AllArgsConstructor
                    public static class FFinalizers {
                        @JsonProperty(".")
                        private Empty empty;
                        @JsonProperty("v:\"in-use.crossplane.io\"")
                        private Empty inUseCrossplane;
                    }
                }

                @Data
                @NoArgsConstructor
                @AllArgsConstructor
                public static class FSpec {
                    @JsonProperty(".")
                    private Empty empty;
                    @JsonProperty("f:credentials")
                    private FCredentials fCredentials;

                    @Data
                    @NoArgsConstructor
                    @AllArgsConstructor
                    public static class FCredentials {
                        @JsonProperty(".")
                        private Empty empty;
                        @JsonProperty("f:secretRef")
                        private FSecretRef fSecretRef;
                        @JsonProperty("f:source")
                        private Empty fSource;

                        @Data
                        @NoArgsConstructor
                        @AllArgsConstructor
                        public static class FSecretRef {
                            @JsonProperty(".")
                            private Empty empty;
                            @JsonProperty("f:key")
                            private Empty fKey;
                            @JsonProperty("f:name")
                            private Empty fName;
                            @JsonProperty("f:namespace")
                            private Empty fNamespace;
                        }
                    }
                }

                @Data
                @NoArgsConstructor
                @AllArgsConstructor
                public static class FStatus {
                    @JsonProperty(".")
                    private Empty empty;
                    @JsonProperty("f:users")
                    private Empty fUsers;
                }
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Spec {
        private Credentials credentials;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Credentials {
            private SecretRef secretRef;
            private String source;

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class SecretRef {
                private String key;
                private String name;
                private String namespace;
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Status {
        private double users;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Empty {}
}