package org.zapovednik.excursionservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.excursionservice.dto.request.DriverRequestDto;
import org.zapovednik.excursionservice.dto.response.DriverResponseDto;
import org.zapovednik.excursionservice.model.entity.Driver;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    DriverResponseDto toDto(final Driver driver);
    Driver toEntity(final DriverRequestDto request);
}