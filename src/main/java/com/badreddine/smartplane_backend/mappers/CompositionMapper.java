package com.badreddine.smartplane_backend.mappers;

import com.badreddine.smartplane_backend.dto.CompositionDto;
import com.badreddine.smartplane_backend.models.CompositionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CompositionMapper {
    CompositionMapper INSTANCE = Mappers.getMapper(CompositionMapper.class);


    @Mapping(source = "metadata.name", target = "name")
    @Mapping(source = "spec.compositeTypeRef.kind", target = "compositeKind")
    @Mapping(source = "spec.compositeTypeRef.apiVersion", target = "compositeGroup")
    CompositionDto toDto(CompositionModel composition);

    List<CompositionDto> compositionListToCompositionDto(List<CompositionModel> composition);
}