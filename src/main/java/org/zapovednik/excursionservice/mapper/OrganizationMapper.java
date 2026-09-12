package org.zapovednik.excursionservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zapovednik.excursionservice.dto.request.OrganizationRequestDto;
import org.zapovednik.excursionservice.dto.response.OrganizationResponseDto;
import org.zapovednik.excursionservice.model.entity.Organization;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    @Mapping(target = "bankId", source = "bank.id")
    @Mapping(target = "bankName", source = "bank.bankName")
    @Mapping(target = "bic", source = "bank.bic")
    OrganizationResponseDto toDto(final Organization organization);

    @Mapping(target = "bank", ignore = true)
    Organization toEntity(final OrganizationRequestDto request);
}