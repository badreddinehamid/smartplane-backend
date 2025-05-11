package com.badreddine.smartplane_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimDto {
    private String name;
    private String kind;
    private String apiVersion;
    private String namespace;

}
