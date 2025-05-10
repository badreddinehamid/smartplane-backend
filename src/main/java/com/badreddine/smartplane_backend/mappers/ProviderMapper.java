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
    ProviderDto providerToProviderDto(ProviderModel provider);

    List<ProviderDto> providerListToProviderDtoList(List<ProviderModel> providers);
}