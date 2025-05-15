package com.badreddine.smartplane_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XrdFilterDto {

        private String name;
        private String namespace;
        private String compositionRef;
        private String ready;

}
