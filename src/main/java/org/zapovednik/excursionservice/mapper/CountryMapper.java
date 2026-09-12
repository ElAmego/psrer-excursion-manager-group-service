package org.zapovednik.excursionservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.excursionservice.dto.request.CountryRequestDto;
import org.zapovednik.excursionservice.dto.response.CountryResponseDto;
import org.zapovednik.excursionservice.model.entity.Country;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryResponseDto toDto(final Country country);
    Country toEntity(final CountryRequestDto request);
}