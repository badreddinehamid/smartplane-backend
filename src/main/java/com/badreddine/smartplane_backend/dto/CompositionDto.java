package com.badreddine.smartplane_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositionDto {
    private String name;
    private String compositeKind;
    private String compositeGroup;
}
