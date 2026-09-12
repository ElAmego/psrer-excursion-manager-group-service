package org.zapovednik.excursionservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.excursionservice.dto.request.RouteRequestDto;
import org.zapovednik.excursionservice.dto.response.RouteResponseDto;
import org.zapovednik.excursionservice.model.entity.Route;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteResponseDto toDto(final Route route);
    Route toEntity(final RouteRequestDto request);
}