package org.zapovednik.excursionservice.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zapovednik.excursionservice.dto.request.DriverRequestDto;
import org.zapovednik.excursionservice.dto.response.DriverResponseDto;
import org.zapovednik.excursionservice.service.DriverService;

@RestController
@RequestMapping("/api/excursion-service/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<Long> save (@Valid @RequestBody final DriverRequestDto requestDto) {
        final Long driverId = driverService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(driverId);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<DriverResponseDto> findById(@PathVariable final Long driverId) {
        final DriverResponseDto responseDto = driverService.findById(driverId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<DriverResponseDto>> findAll() {
        final List<DriverResponseDto> responseDtoList = driverService.findAll();

        return ResponseEntity.ok(responseDtoList);
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<DriverResponseDto> updateById(
            @PathVariable final Long driverId,
            @Valid @RequestBody DriverRequestDto requestDto
    ) {
        final DriverResponseDto responseDto = driverService.updateById(driverId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long driverId) {
        driverService.deleteById(driverId);

        return ResponseEntity.noContent().build();
    }
}