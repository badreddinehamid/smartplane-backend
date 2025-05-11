package com.badreddine.smartplane_backend.mappers;

import com.badreddine.smartplane_backend.dto.CompositeResourcesDto;
import com.badreddine.smartplane_backend.models.CompositeResourcesModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompositeResourcesMapper {

    CompositeResourcesMapper INSTANCE = Mappers.getMapper(CompositeResourcesMapper.class);

    @Mapping(source = "metadata.name", target = "composition")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "apiVersion", target = "group") // You can extract group from apiVersion if needed
    @Mapping(source = "status.resources", target = "resources")
    @Mapping(source = "status.conditions", target = "conditions")
    CompositeResourcesDto toDto(CompositeResourcesModel model);

    List<CompositeResourcesDto> xrListToxrDto(List<CompositeResourcesModel> compositeResources);
}
