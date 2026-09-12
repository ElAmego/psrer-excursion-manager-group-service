package org.zapovednik.excursionservice.service;

import java.util.List;
import org.zapovednik.excursionservice.dto.request.DriverRequestDto;
import org.zapovednik.excursionservice.dto.response.DriverResponseDto;

public interface DriverService {
    Long save(final DriverRequestDto requestDto);
    DriverResponseDto findById(final Long driverId);
    List<DriverResponseDto> findAll();
    DriverResponseDto updateById(final Long driverId, final DriverRequestDto requestDto);
    void deleteById(final Long driverId);
}