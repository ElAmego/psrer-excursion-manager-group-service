package org.zapovednik.excursionservice.service;

import java.time.LocalDate;
import java.util.List;
import org.zapovednik.excursionservice.dto.request.OrganizationRequestDto;
import org.zapovednik.excursionservice.dto.response.OrganizationResponseDto;
import org.zapovednik.excursionservice.model.repository.projection.OrganizationGroupCountProjection;
import org.zapovednik.excursionservice.model.repository.projection.OrganizationParticipantQuantitySumProjection;

public interface OrganizationService {
    Long save(final OrganizationRequestDto requestDto);
    OrganizationResponseDto findById(final Long organizationId);
    List<OrganizationResponseDto> findAll();
    OrganizationResponseDto updateById(final Long organizationId, final OrganizationRequestDto requestDto);
    void deleteById(final Long organizationId);
    List<OrganizationGroupCountProjection> findTop3ByGroupCount(
            final LocalDate startDate,
            final LocalDate endDate
    );
    List<OrganizationParticipantQuantitySumProjection> findTop3ByParticipantQuantitySum(
            final LocalDate startDate,
            final LocalDate endDate
    );
}