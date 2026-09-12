package org.zapovednik.excursionservice.service;

import java.util.List;
import org.zapovednik.excursionservice.dto.request.RouteRequestDto;
import org.zapovednik.excursionservice.dto.response.RouteResponseDto;

public interface RouteService {
    Long save(final RouteRequestDto requestDto);
    RouteResponseDto findById(final Long routeId);
    List<RouteResponseDto> findAll();
    RouteResponseDto updateById(final Long routeId, final RouteRequestDto requestDto);
    void deleteById(final Long routeId);
}