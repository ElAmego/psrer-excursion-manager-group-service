package org.zapovednik.excursionservice.controller;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zapovednik.excursionservice.dto.request.GroupCountryRequestDto;
import org.zapovednik.excursionservice.dto.response.GroupCountryResponseDto;
import org.zapovednik.excursionservice.model.entity.type.ExcursionGroupStatus;
import org.zapovednik.excursionservice.model.repository.projection.CountryStatisticsProjection;
import org.zapovednik.excursionservice.service.GroupCountryService;

@RestController
@RequestMapping("/api/excursion-service/excursions")
@RequiredArgsConstructor
public class GroupCountryController {
    private final GroupCountryService groupCountryService;

    @PostMapping("/{excursionGroupId}/group-countries")
    public ResponseEntity<Long> save(
            @PathVariable final Long excursionGroupId,
            @Valid @RequestBody final GroupCountryRequestDto requestDto
    ) {
        final Long groupCountryId = groupCountryService.save(excursionGroupId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(groupCountryId);
    }

    @GetMapping("/{excursionGroupId}/group-countries")
    public ResponseEntity<List<GroupCountryResponseDto>> findAllByExcursionGroupId(
            @PathVariable final Long excursionGroupId
    ) {
        final List<GroupCountryResponseDto> responseDtoList = groupCountryService
                .findAllByExcursionGroupId(excursionGroupId);

        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/group-countries/sum-participant-quantity-by-status-and-dates")
    public ResponseEntity<Long> sumParticipantQuantityByStatusAndStartDateBetween(
            @RequestParam final ExcursionGroupStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final Long participantQuantity = groupCountryService
                .sumParticipantQuantityByStatusAndStartDateBetween(status, startDate, endDate);

        return ResponseEntity.ok(participantQuantity);
    }

    @GetMapping("/group-countries/sum-participant-quantity-by-is-legal-status-and-dates")
    public ResponseEntity<Long> sumParticipantQuantityByIsLegalAndStatusAndStartDateBetween(
            @RequestParam final Boolean isLegal,
            @RequestParam final ExcursionGroupStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final Long participantQuantity = groupCountryService
                .sumParticipantQuantityByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);

        return ResponseEntity.ok(participantQuantity);
    }

    @GetMapping("/group-countries/country-statistics-by-status-completed-and-dates")
    public ResponseEntity<List<CountryStatisticsProjection>> findCountryStatisticsByStatusCompletedAndStartDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final List<CountryStatisticsProjection> countryStatisticsProjectionList = groupCountryService
                .findCountryStatisticsByStatusCompletedAndStartDateBetween(startDate, endDate);

        return ResponseEntity.ok(countryStatisticsProjectionList);
    }

    @GetMapping("/group-countries/{organizationId}/sum-participant-quantity-by-organization-id-status-and-dates")
    public ResponseEntity<Long> sumParticipantQuantityByOrganizationIdAndStatusAndStartDateBetween(
            @PathVariable final Long organizationId,
            @RequestParam final ExcursionGroupStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final Long participantQuantity = groupCountryService
                .sumParticipantQuantityByOrganizationIdAndStatusAndStartDateBetween(
                        organizationId, status, startDate, endDate);

        return ResponseEntity.ok(participantQuantity);
    }

    @GetMapping("/group-countries/{routeId}/sum-participant-quantity-by-route-id-status-completed-and-dates")
    public ResponseEntity<Long> sumParticipantQuantityByRouteIdAndStatusCompletedAndStartDateBetween(
            @PathVariable final Long routeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final Long participantQuantity = groupCountryService
                .sumParticipantQuantityByRouteIdAndStatusCompletedAndStartDateBetween(routeId, startDate, endDate);

        return ResponseEntity.ok(participantQuantity);
    }

    @PutMapping("/group-countries/{groupCountryId}")
    public ResponseEntity<GroupCountryResponseDto> updateById(
            @PathVariable final Long groupCountryId,
            @Valid @RequestBody final GroupCountryRequestDto requestDto
    ) {
        final GroupCountryResponseDto responseDto = groupCountryService.updateById(groupCountryId, requestDto);

        return ResponseEntity.ok(responseDto);
    }
}