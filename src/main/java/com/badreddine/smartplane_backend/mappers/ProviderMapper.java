package com.badreddine.smartplane_backend.mappers;

import com.badreddine.smartplane_backend.dto.ProviderDto;
import com.badreddine.smartplane_backend.models.ProviderModel;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface ProviderMapper {
//    ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);
//    @Mapping(target = "apiVersion", source = "apiVersion")
//    @Mapping(target = "kind", source = "kind")
    ProviderDto ProviderListToProviderDto(ProviderModel.ProviderList providerList);
}