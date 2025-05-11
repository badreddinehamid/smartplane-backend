package com.badreddine.smartplane_backend.dto;


import com.badreddine.smartplane_backend.models.XrdsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class XrdsDto {

    private String name;
    private String group;
    private XrdsModel.Names names;



}