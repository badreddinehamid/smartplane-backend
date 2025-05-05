package com.badreddine.smartplane_backend.mappers;

import com.badreddine.smartplane_backend.dto.ProviderConfigDto;
import com.badreddine.smartplane_backend.models.ProviderConfigModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ProviderConfigMapper {
    ProviderConfigMapper INSTANCE = Mappers.getMapper(ProviderConfigMapper.class);


    ProviderConfigDto toDTO(ProviderConfigModel.ProviderConfigList providerconfiglist);
}