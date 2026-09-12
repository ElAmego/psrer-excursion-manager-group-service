package org.zapovednik.excursionservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.excursionservice.dto.request.RouteRequestDto;
import org.zapovednik.excursionservice.dto.response.RouteResponseDto;
import org.zapovednik.excursionservice.exception.custom.NotFoundException;
import org.zapovednik.excursionservice.mapper.RouteMapper;
import org.zapovednik.excursionservice.model.entity.Route;
import org.zapovednik.excursionservice.model.repository.RouteRepository;
import org.zapovednik.excursionservice.service.RouteService;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    @Override
    @Transactional
    public Long save(final RouteRequestDto requestDto) {
        final Route route = routeMapper.toEntity(requestDto);
        final Route savedRoute = routeRepository.save(route);

        return savedRoute.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public RouteResponseDto findById(final Long routeId) {
        final Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new NotFoundException("Route not found: " + routeId));

        return routeMapper.toDto(route);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RouteResponseDto> findAll() {
        final List<Route> routeList = routeRepository.findAll();

        return routeList.stream()
                .map(routeMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public RouteResponseDto updateById(final Long routeId, final RouteRequestDto requestDto) {
        final Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new NotFoundException("Route not found: " + routeId));

        route.setRouteName(requestDto.getRouteName());
        route.setRouteNum(requestDto.getRouteNum());

        return routeMapper.toDto(route);
    }

    @Override
    @Transactional
    public void deleteById(final Long routeId) {
        if (routeRepository.existsById(routeId)) {
            routeRepository.deleteById(routeId);
        } else {
            throw new NotFoundException("Route not found: " + routeId);
        }
    }
}