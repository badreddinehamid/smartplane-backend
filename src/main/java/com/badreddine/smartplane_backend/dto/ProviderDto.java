package com.badreddine.smartplane_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProviderDto {
    private String name;
    private String url;

public ProviderDto(){
// Default constructor required for  Jackson to deserialize JSON into this object

}


}