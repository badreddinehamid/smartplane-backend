package com.badreddine.smartplane_backend.dto;

import com.badreddine.smartplane_backend.models.ProviderModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
public class ProviderDto {
    private String apiVersion;

    private String kind;
public ProviderDto(){}

    public ProviderDto(String apiVersion, String kind) {
        this.apiVersion = apiVersion;
        this.kind = kind;}
}