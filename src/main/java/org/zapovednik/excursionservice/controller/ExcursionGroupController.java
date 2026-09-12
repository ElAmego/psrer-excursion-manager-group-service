package org.zapovednik.excursionservice.controller;

import jakarta.validation.Valid;
import java.math.BigDecimal;
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
import org.zapovednik.excursionservice.dto.request.ExcursionGroupExpectationRequestDto;
import org.zapovednik.excursionservice.dto.request.ExcursionGroupProcessingRequestDto;
import org.zapovednik.excursionservice.dto.request.ExcursionGroupStatusUpdateRequestDto;
import org.zapovednik.excursionservice.dto.response.ExcursionGroupExpectationResponseDto;
import org.zapovednik.excursionservice.dto.response.ExcursionGroupResponseDto;
import org.zapovednik.excursionservice.model.entity.type.ExcursionGroupStatus;
import org.zapovednik.excursionservice.service.ExcursionGroupService;

@RestController
@RequestMapping("/api/excursion-service/excursions")
@RequiredArgsConstructor
public class ExcursionGroupController {
    private final ExcursionGroupService excursionGroupService;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody final ExcursionGroupExpectationRequestDto requestDto) {
        final Long excursionGroupId = excursionGroupService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(excursionGroupId);
    }

    @GetMapping("/{excursionGroupId}")
    public ResponseEntity<ExcursionGroupResponseDto> findById(@PathVariable final Long excursionGroupId) {
        final ExcursionGroupResponseDto responseDto = excursionGroupService.findById(excursionGroupId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/by-statuses")
    public ResponseEntity<List<ExcursionGroupResponseDto>> findAllByStatusIn(
            @RequestParam final List<ExcursionGroupStatus> excursionGroupStatuses
    ) {
        final List<ExcursionGroupResponseDto> responseDtoList = excursionGroupService
                .findAllByStatusIn(excursionGroupStatuses);

        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/by-dates-and-statuses")
    public ResponseEntity<List<ExcursionGroupResponseDto>> findAllByStartDateBetweenAndStatusIn(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate,
            @RequestParam final List<ExcursionGroupStatus> excursionGroupStatuses
    ) {
        final List<ExcursionGroupResponseDto> responseDtoList = excursionGroupService
                .findAllByStartDateBetweenAndStatusIn(startDate, endDate, excursionGroupStatuses);

        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/by-dates-organization-id-and-statuses")
    public ResponseEntity<List<ExcursionGroupResponseDto>> findAllByStartDateBetweenAndOrganizationIdAndStatusIn(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate,
            @RequestParam final Long organizationId,
            @RequestParam final List<ExcursionGroupStatus> excursionGroupStatuses
    ) {
        final List<ExcursionGroupResponseDto> responseDtoList = excursionGroupService
                .findAllByStartDateBetweenAndOrganizationIdAndStatusIn(startDate, endDate, organizationId, excursionGroupStatuses);

        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/by-dates-statuses-and-is-paid")
    public ResponseEntity<List<ExcursionGroupResponseDto>> findAllByStartDateBetweenAndStatusCompletedAndIsPaid(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate,
            @RequestParam final Boolean isPaid
    ) {
        final List<ExcursionGroupResponseDto> responseDtoList = excursionGroupService
                .findAllByStartDateBetweenAndStatusCompletedAndIsPaid(startDate, endDate, isPaid);

        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/by-dates-statuses-and-is-documents-submitted")
    public ResponseEntity<List<ExcursionGroupResponseDto>> findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmitted(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate,
            @RequestParam final Boolean isDocumentsSubmitted
    ) {
        final List<ExcursionGroupResponseDto> responseDtoList = excursionGroupService
                .findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmitted(startDate, endDate, isDocumentsSubmitted);

        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/count-by-status-and-dates")
    public ResponseEntity<Long> countByStatusAndStartDateBetween(
            @RequestParam final ExcursionGroupStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final Long excursionGroupQuantity = excursionGroupService
                .countByStatusAndStartDateBetween(status, startDate, endDate);

        return ResponseEntity.ok(excursionGroupQuantity);
    }

    @GetMapping("/count-by-is-legal-status-and-dates")
    public ResponseEntity<Long> countByIsLegalAndStatusAndStartDateBetween(
            @RequestParam final Boolean isLegal,
            @RequestParam final ExcursionGroupStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final Long excursionGroupQuantity = excursionGroupService
                .countByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);

        return ResponseEntity.ok(excursionGroupQuantity);
    }

    @GetMapping("/sum-by-status-and-dates")
    public ResponseEntity<BigDecimal> sumPriceByStatusAndStartDateBetween(
            @RequestParam final ExcursionGroupStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final BigDecimal sumPrice = excursionGroupService
                .sumPriceByStatusAndStartDateBetween(status, startDate, endDate);

        return ResponseEntity.ok(sumPrice);
    }

    @GetMapping("/sum-by-is-legal-status-and-dates")
    public ResponseEntity<BigDecimal> sumPriceByIsLegalAndStatusAndStartDateBetween(
            @RequestParam final Boolean isLegal,
            @RequestParam final ExcursionGroupStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final BigDecimal sumPrice = excursionGroupService
                .sumPriceByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);

        return ResponseEntity.ok(sumPrice);
    }

    @GetMapping("/count-by-organization-id-status-and-dates")
    public ResponseEntity<Long> countByOrganizationIdAndStatusAndStartDateBetween(
            @RequestParam final Long organizationId,
            @RequestParam final ExcursionGroupStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final Long excursionGroupQuantity = excursionGroupService
                .countByOrganizationIdAndStatusAndStartDateBetween(organizationId, status, startDate, endDate);

        return ResponseEntity.ok(excursionGroupQuantity);
    }

    @GetMapping("/sum-by-organization-id-status-and-dates")
    public ResponseEntity<BigDecimal> sumPriceByOrganizationIdAndStatusAndStartDateBetween(
            @RequestParam final Long organizationId,
            @RequestParam final ExcursionGroupStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final BigDecimal sumPrice = excursionGroupService
                .sumPriceByOrganizationIdAndStatusAndStartDateBetween(organizationId, status, startDate, endDate);

        return ResponseEntity.ok(sumPrice);
    }

    @GetMapping("/count-by-driver-id-status-completed-and-dates")
    public ResponseEntity<Long> countByDriverIdAndStatusCompletedAndStartDateBetween(
            @RequestParam final Long driverId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final Long excursionGroupQuantity = excursionGroupService
                .countByDriverIdAndStatusCompletedAndStartDateBetween(driverId, startDate, endDate);

        return ResponseEntity.ok(excursionGroupQuantity);
    }

    @GetMapping("/count-by-accompanying-person-id-status-completed-and-dates")
    public ResponseEntity<Long> countByAccompanyingPersonIdAndStatusCompletedAndStartDateBetween(
            @RequestParam final Long accompanyingPersonId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final Long excursionGroupQuantity = excursionGroupService
                .countByAccompanyingPersonIdAndStatusCompletedAndStartDateBetween(accompanyingPersonId, startDate, endDate);

        return ResponseEntity.ok(excursionGroupQuantity);
    }

    @GetMapping("/count-by-route-id-status-completed-and-dates")
    public ResponseEntity<Long> countByRouteIdAndStatusCompletedAndStartDateBetween(
            @RequestParam final Long routeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final Long excursionGroupQuantity = excursionGroupService
                .countByRouteIdAndStatusCompletedAndStartDateBetween(routeId, startDate, endDate);

        return ResponseEntity.ok(excursionGroupQuantity);
    }

    @GetMapping("/sum-by-route-id-status-completed-and-dates")
    public ResponseEntity<BigDecimal> sumPriceByRouteIdAndStatusCompletedAndStartDateBetween(
            @RequestParam final Long routeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate endDate
    ) {
        final BigDecimal sumPrice = excursionGroupService
                .sumPriceByRouteIdAndStatusCompletedAndStartDateBetween(routeId, startDate, endDate);

        return ResponseEntity.ok(sumPrice);
    }

    @PutMapping("/update-status/{excursionGroupId}")
    public ResponseEntity<ExcursionGroupResponseDto> updateExcursionGroupStatus(
            @PathVariable final Long excursionGroupId,
            @Valid @RequestBody final ExcursionGroupStatusUpdateRequestDto requestDto
    ) {
        final ExcursionGroupResponseDto responseDto = excursionGroupService
                .updateExcursionGroupStatus(excursionGroupId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update-expectation-status/{excursionGroupId}")
    public ResponseEntity<ExcursionGroupExpectationResponseDto> updateExpectationStatus(
            @PathVariable final Long excursionGroupId,
            @Valid @RequestBody final ExcursionGroupExpectationRequestDto requestDto
    ) {
        final ExcursionGroupExpectationResponseDto responseDto = excursionGroupService
                .updateExpectationStatus(excursionGroupId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update-processing-status/{excursionGroupId}")
    public ResponseEntity<ExcursionGroupResponseDto> updateProcessingStatus(
            @PathVariable final Long excursionGroupId,
            @Valid @RequestBody final ExcursionGroupProcessingRequestDto requestDto
    ) {
        final ExcursionGroupResponseDto responseDto = excursionGroupService
                .updateProcessingStatus(excursionGroupId, requestDto);

        return ResponseEntity.ok(responseDto);
    }
}