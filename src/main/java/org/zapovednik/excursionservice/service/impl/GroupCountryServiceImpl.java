package org.zapovednik.excursionservice.service.impl;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.excursionservice.dto.request.GroupCountryRequestDto;
import org.zapovednik.excursionservice.dto.response.GroupCountryResponseDto;
import org.zapovednik.excursionservice.exception.custom.NotFoundException;
import org.zapovednik.excursionservice.mapper.GroupCountryMapper;
import org.zapovednik.excursionservice.model.entity.Country;
import org.zapovednik.excursionservice.model.entity.GroupCountry;
import org.zapovednik.excursionservice.model.entity.ExcursionGroup;
import org.zapovednik.excursionservice.model.entity.type.ExcursionGroupStatus;
import org.zapovednik.excursionservice.model.repository.CountryRepository;
import org.zapovednik.excursionservice.model.repository.GroupCountryRepository;
import org.zapovednik.excursionservice.model.repository.OrganizationRepository;
import org.zapovednik.excursionservice.model.repository.RouteRepository;
import org.zapovednik.excursionservice.model.repository.ExcursionGroupRepository;
import org.zapovednik.excursionservice.model.repository.projection.CountryStatisticsProjection;
import org.zapovednik.excursionservice.service.GroupCountryService;

@Service
@RequiredArgsConstructor
public class GroupCountryServiceImpl implements GroupCountryService {
    private final GroupCountryRepository groupCountryRepository;
    private final CountryRepository countryRepository;
    private final ExcursionGroupRepository excursionGroupRepository;
    private final OrganizationRepository organizationRepository;
    private final RouteRepository routeRepository;
    private final GroupCountryMapper groupCountryMapper;

    @Override
    @Transactional
    public Long save(final Long excursionGroupId, final GroupCountryRequestDto requestDto) {
        final ExcursionGroup excursionGroup = excursionGroupRepository.findById(excursionGroupId)
                .orElseThrow(() -> new NotFoundException("Excursion group not found: " + excursionGroupId));
        final Long countryId = requestDto.getCountryId();
        final Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Country not found: " + countryId));
        final GroupCountry groupCountry = groupCountryMapper.toEntity(requestDto);

        groupCountry.setExcursionGroup(excursionGroup);
        groupCountry.setCountry(country);

        final GroupCountry savedGroupCountry = groupCountryRepository.save(groupCountry);

        return savedGroupCountry.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupCountryResponseDto> findAllByExcursionGroupId(final Long excursionGroupId) {
        if (excursionGroupRepository.existsById(excursionGroupId)) {
            final List<GroupCountry> groupCountryList = groupCountryRepository
                    .findAllByExcursionGroupId(excursionGroupId);

            return groupCountryList.stream()
                    .map(groupCountryMapper::toDto)
                    .toList();
        } else {
            throw new NotFoundException("Excursion group not found: " + excursionGroupId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long sumParticipantQuantityByStatusAndStartDateBetween(
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return groupCountryRepository.sumParticipantQuantityByStatusAndStartDateBetween(status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Long sumParticipantQuantityByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return groupCountryRepository
                .sumParticipantQuantityByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryStatisticsProjection> findCountryStatisticsByStatusCompletedAndStartDateBetween(
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return groupCountryRepository.findCountryStatisticsByStatusCompletedAndStartDateBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Long sumParticipantQuantityByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (organizationRepository.existsById(organizationId)) {
            return groupCountryRepository
                    .sumParticipantQuantityByOrganizationIdAndStatusAndStartDateBetween(
                            organizationId, status, startDate, endDate);
        } else {
            throw new NotFoundException("Organization not found: " + organizationId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long sumParticipantQuantityByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (routeRepository.existsById(routeId)) {
            return groupCountryRepository
                    .sumParticipantQuantityByRouteIdAndStatusCompletedAndStartDateBetween(
                            routeId, startDate, endDate);
        } else {
            throw new NotFoundException("Route not found: " + routeId);
        }
    }

    @Override
    @Transactional
    public GroupCountryResponseDto updateById(final Long groupCountryId, final GroupCountryRequestDto requestDto) {
        final GroupCountry groupCountry = groupCountryRepository.findById(groupCountryId)
                .orElseThrow(() -> new NotFoundException("Group country not found: " + groupCountryId));
        final Long countryId = requestDto.getCountryId();
        final Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Country not found: " + countryId));

        groupCountry.setCountry(country);
        groupCountry.setParticipantQuantity(requestDto.getParticipantQuantity());

        return groupCountryMapper.toDto(groupCountry);
    }
}