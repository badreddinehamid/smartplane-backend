package com.badreddine.smartplane_backend.mappers;

import com.badreddine.smartplane_backend.dto.ProviderDto;
import com.badreddine.smartplane_backend.models.ProviderModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ProviderMapper {
    ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);
    ProviderDto ProviderListToProviderDto(ProviderModel.ProviderList providerModel);

}