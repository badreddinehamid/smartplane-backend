package com.badreddine.smartplane_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderFilterDto {
    private String name;
    private String namespace;
    private String packageName;
    private Boolean healthy;
    private Boolean installed;
    private Map<String, String> labels;

}
