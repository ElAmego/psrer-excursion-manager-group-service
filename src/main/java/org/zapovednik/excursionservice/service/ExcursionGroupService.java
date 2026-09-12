package org.zapovednik.excursionservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.zapovednik.excursionservice.dto.request.ExcursionGroupExpectationRequestDto;
import org.zapovednik.excursionservice.dto.request.ExcursionGroupProcessingRequestDto;
import org.zapovednik.excursionservice.dto.request.ExcursionGroupStatusUpdateRequestDto;
import org.zapovednik.excursionservice.dto.response.ExcursionGroupExpectationResponseDto;
import org.zapovednik.excursionservice.dto.response.ExcursionGroupResponseDto;
import org.zapovednik.excursionservice.model.entity.type.ExcursionGroupStatus;

public interface ExcursionGroupService {
    Long save(final ExcursionGroupExpectationRequestDto requestDto);
    ExcursionGroupResponseDto findById(final Long excursionGroupId);
    List<ExcursionGroupResponseDto> findAllByStatusIn(final List<ExcursionGroupStatus> excursionGroupStatuses);
    List<ExcursionGroupResponseDto> findAllByStartDateBetweenAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final List<ExcursionGroupStatus> excursionGroupStatuses
    );
    List<ExcursionGroupResponseDto> findAllByStartDateBetweenAndOrganizationIdAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final Long organizationId,
            final List<ExcursionGroupStatus> excursionGroupStatuses
    );
    List<ExcursionGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsPaid(
            final LocalDate startDate,
            final LocalDate endDate,
            final Boolean isPaid
    );
    List<ExcursionGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmitted(
            final LocalDate startDate,
            final LocalDate endDate,
            final Boolean isDocumentsSubmitted
    );
    Long countByStatusAndStartDateBetween(
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    BigDecimal sumPriceByStatusAndStartDateBetween(
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    BigDecimal sumPriceByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    BigDecimal sumPriceByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByDriverIdAndStatusCompletedAndStartDateBetween(
            final Long driverId,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByAccompanyingPersonIdAndStatusCompletedAndStartDateBetween(
            final Long accompanyingPersonId,
            final LocalDate startDate,
            final LocalDate endDate
    );
    Long countByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    );
    BigDecimal sumPriceByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    );
    ExcursionGroupResponseDto updateExcursionGroupStatus(
            final Long excursionGroupId,
            final ExcursionGroupStatusUpdateRequestDto requestDto
    );
    ExcursionGroupExpectationResponseDto updateExpectationStatus(
            final Long excursionGroupId,
            final ExcursionGroupExpectationRequestDto requestDto
    );
    ExcursionGroupResponseDto updateProcessingStatus(
            final Long excursionGroupId,
            final ExcursionGroupProcessingRequestDto requestDto
    );
}