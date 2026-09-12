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
import org.zapovednik.excursionservice.dto.request.RouteRequestDto;
import org.zapovednik.excursionservice.dto.response.RouteResponseDto;
import org.zapovednik.excursionservice.service.RouteService;

@RestController
@RequestMapping("/api/excursion-service/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<Long> save (@Valid @RequestBody final RouteRequestDto requestDto) {
        final Long routeId = routeService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(routeId);
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<RouteResponseDto> findById(@PathVariable final Long routeId) {
        final RouteResponseDto responseDto = routeService.findById(routeId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<RouteResponseDto>> findAll() {
        final List<RouteResponseDto> responseDtoList = routeService.findAll();

        return ResponseEntity.ok(responseDtoList);
    }

    @PutMapping("/{routeId}")
    public ResponseEntity<RouteResponseDto> updateById(
            @PathVariable final Long routeId,
            @Valid @RequestBody RouteRequestDto requestDto
    ) {
        final RouteResponseDto responseDto = routeService.updateById(routeId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{routeId}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long routeId) {
        routeService.deleteById(routeId);

        return ResponseEntity.noContent().build();
    }
}