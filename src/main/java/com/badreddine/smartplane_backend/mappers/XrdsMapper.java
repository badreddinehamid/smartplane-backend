package com.badreddine.smartplane_backend.mappers;

import com.badreddine.smartplane_backend.dto.XrdsDto;
import com.badreddine.smartplane_backend.models.XrdsModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface XrdsMapper {
    XrdsMapper INSTANCE = Mappers.getMapper(XrdsMapper.class);


    @Mapping(source = "metadata.name", target = "name")
    @Mapping(source = "spec.group", target = "group")
    @Mapping(source = "spec.names",target = "names")
    XrdsDto toDto(XrdsModel xrds);

    List<XrdsDto> xrdsListToDtoList(List<XrdsModel> xrds);
}