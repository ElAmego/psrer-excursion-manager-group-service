package org.zapovednik.excursionservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.excursionservice.dto.request.DriverRequestDto;
import org.zapovednik.excursionservice.dto.response.DriverResponseDto;
import org.zapovednik.excursionservice.exception.custom.NotFoundException;
import org.zapovednik.excursionservice.mapper.DriverMapper;
import org.zapovednik.excursionservice.model.entity.Driver;
import org.zapovednik.excursionservice.model.repository.DriverRepository;
import org.zapovednik.excursionservice.service.DriverService;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    @Transactional
    public Long save(final DriverRequestDto requestDto) {
        final Driver driver = driverMapper.toEntity(requestDto);
        final Driver savedDriver = driverRepository.save(driver);

        return savedDriver.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public DriverResponseDto findById(final Long driverId) {
        final Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new NotFoundException("Driver not found: " + driverId));

        return driverMapper.toDto(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverResponseDto> findAll() {
        final List<Driver> driverList = driverRepository.findAll();

        return driverList.stream()
                .map(driverMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public DriverResponseDto updateById(final Long driverId, final DriverRequestDto requestDto) {
        final Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new NotFoundException("Driver not found: " + driverId));

        driver.setDriverName(requestDto.getDriverName());
        driver.setDriverPhoneNumber(requestDto.getDriverPhoneNumber());

        return driverMapper.toDto(driver);
    }

    @Override
    @Transactional
    public void deleteById(final Long driverId) {
        if (driverRepository.existsById(driverId)) {
            driverRepository.deleteById(driverId);
        } else {
            throw new NotFoundException("Driver not found: " + driverId);
        }
    }
}