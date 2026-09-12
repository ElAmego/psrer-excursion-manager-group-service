package org.zapovednik.excursionservice.controller;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zapovednik.excursionservice.dto.request.OrganizationRequestDto;
import org.zapovednik.excursionservice.dto.response.OrganizationResponseDto;
import org.zapovednik.excursionservice.model.repository.projection.OrganizationGroupCountProjection;
import org.zapovednik.excursionservice.model.repository.projection.OrganizationParticipantQuantitySumProjection;
import org.zapovednik.excursionservice.service.OrganizationService;

@RestController
@RequestMapping("/api/excursion-service/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody final OrganizationRequestDto requestDto) {
        final Long organizationId = organizationService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(organizationId);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponseDto> findById(@PathVariable final Long organizationId) {
        final OrganizationResponseDto responseDto = organizationService.findById(organizationId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDto>> findAll() {
        final List<OrganizationResponseDto> responseDtoList = organizationService.findAll();

        return ResponseEntity.ok(responseDtoList);
    }

    @PutMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponseDto> updateById(
            @PathVariable final Long organizationId,
            @Valid @RequestBody final OrganizationRequestDto requestDto
    ) {
        final OrganizationResponseDto responseDto = organizationService.updateById(organizationId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{organizationId}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long organizationId) {
        organizationService.deleteById(organizationId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top3-by-group-count")
    public ResponseEntity<List<OrganizationGroupCountProjection>> findTop3ByGroupCount(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final List<OrganizationGroupCountProjection> resultList = organizationService
                .findTop3ByGroupCount(startDate, endDate);

        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/top3-by-participant-quantity")
    public ResponseEntity<List<OrganizationParticipantQuantitySumProjection>> findTop3ByParticipantQuantitySum(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final List<OrganizationParticipantQuantitySumProjection> resultList = organizationService
                .findTop3ByParticipantQuantitySum(startDate, endDate);

        return ResponseEntity.ok(resultList);
    }
}