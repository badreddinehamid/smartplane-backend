package com.badreddine.smartplane_backend.mappers;

import com.badreddine.smartplane_backend.dto.ProviderDto;
import com.badreddine.smartplane_backend.models.provider.ProviderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProviderMapper {
        ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);
    @Mapping(source = "metadata.name", target = "name")
    @Mapping(source = "spec.pkg", target = "url")
    ProviderDto providerToProviderDto(ProviderModel provider);

    List<ProviderDto> providerListToProviderDtoList(List<ProviderModel> providers);
}