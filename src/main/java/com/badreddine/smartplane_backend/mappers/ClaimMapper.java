package com.badreddine.smartplane_backend.mappers;

import com.badreddine.smartplane_backend.dto.ClaimDto;
import com.badreddine.smartplane_backend.models.ClaimsModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClaimMapper {

    ClaimMapper INSTANCE = Mappers.getMapper(ClaimMapper.class);

    @Mapping(source = "metadata.name", target = "name")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "apiVersion", target = "apiVersion")
    @Mapping(source = "metadata.namespace", target = "namespace")
    ClaimDto toDto(ClaimsModel model);

    List<ClaimDto> claimListToClaimDto(List<ClaimsModel> claims);
}