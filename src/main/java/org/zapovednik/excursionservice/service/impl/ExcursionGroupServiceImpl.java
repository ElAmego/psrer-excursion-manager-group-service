package org.zapovednik.excursionservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.excursionservice.dto.request.ExcursionGroupExpectationRequestDto;
import org.zapovednik.excursionservice.dto.request.ExcursionGroupProcessingRequestDto;
import org.zapovednik.excursionservice.dto.request.ExcursionGroupStatusUpdateRequestDto;
import org.zapovednik.excursionservice.dto.response.ExcursionGroupExpectationResponseDto;
import org.zapovednik.excursionservice.dto.response.ExcursionGroupResponseDto;
import org.zapovednik.excursionservice.exception.custom.NotFoundException;
import org.zapovednik.excursionservice.mapper.ExcursionGroupMapper;
import org.zapovednik.excursionservice.model.entity.Driver;
import org.zapovednik.excursionservice.model.entity.ExcursionGroup;
import org.zapovednik.excursionservice.model.entity.Organization;
import org.zapovednik.excursionservice.model.entity.AccompanyingPerson;
import org.zapovednik.excursionservice.model.entity.Route;
import org.zapovednik.excursionservice.model.entity.type.ExcursionGroupStatus;
import org.zapovednik.excursionservice.model.repository.AccompanyingPersonRepository;
import org.zapovednik.excursionservice.model.repository.DriverRepository;
import org.zapovednik.excursionservice.model.repository.OrganizationRepository;
import org.zapovednik.excursionservice.model.repository.RouteRepository;
import org.zapovednik.excursionservice.model.repository.ExcursionGroupRepository;
import org.zapovednik.excursionservice.service.ExcursionGroupService;

@Service
@RequiredArgsConstructor
public class ExcursionGroupServiceImpl implements ExcursionGroupService {
    private final ExcursionGroupRepository excursionGroupRepository;
    private final RouteRepository routeRepository;
    private final OrganizationRepository organizationRepository;
    private final ExcursionGroupMapper excursionGroupMapper;
    private final DriverRepository driverRepository;
    private final AccompanyingPersonRepository accompanyingPersonRepository;

    @Override
    @Transactional
    public Long save(final ExcursionGroupExpectationRequestDto requestDto) {
        final ExcursionGroup excursionGroup = excursionGroupMapper.toCreateEntity(requestDto);
        final Long routeId = requestDto.getRouteId();

        excursionGroup.setStatus(ExcursionGroupStatus.EXPECTATION);
        excursionGroup.setRoute(routeRepository.getReferenceById(routeId));

        if (requestDto.getIsLegal()) {
            final Long organizationId = requestDto.getOrganizationId();

            excursionGroup.setOrganization(organizationRepository.getReferenceById(organizationId));
        }

        final ExcursionGroup savedExcursionGroup = excursionGroupRepository.save(excursionGroup);

        return savedExcursionGroup.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public ExcursionGroupResponseDto findById(final Long excursionGroupId) {
        final ExcursionGroup excursionGroup = excursionGroupRepository.findById(excursionGroupId)
                .orElseThrow(() -> new NotFoundException("Excursion group not found: " + excursionGroupId));

        return excursionGroupMapper.toDto(excursionGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExcursionGroupResponseDto> findAllByStatusIn(final List<ExcursionGroupStatus> excursionGroupStatuses) {
        final List<ExcursionGroup> excursionGroupList = excursionGroupRepository.findAllByStatusIn(excursionGroupStatuses);

        return excursionGroupList.stream()
                .map(excursionGroupMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExcursionGroupResponseDto> findAllByStartDateBetweenAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final List<ExcursionGroupStatus> excursionGroupStatuses
    ) {
        final List<ExcursionGroup> excursionGroupList = excursionGroupRepository
                .findAllByStartDateBetweenAndStatusIn(startDate, endDate, excursionGroupStatuses);

        return excursionGroupList.stream()
                .map(excursionGroupMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExcursionGroupResponseDto> findAllByStartDateBetweenAndOrganizationIdAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final Long organizationId,
            final List<ExcursionGroupStatus> excursionGroupStatuses
    ) {
        if (!organizationRepository.existsById(organizationId)) {
            throw new NotFoundException("Organization not found: " + organizationId);
        }

        final List<ExcursionGroup> excursionGroupList = excursionGroupRepository
                .findAllByStartDateBetweenAndOrganizationIdAndStatusIn(startDate, endDate, organizationId,
                        excursionGroupStatuses);

        return excursionGroupList.stream()
                .map(excursionGroupMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExcursionGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsPaid(
            final LocalDate startDate,
            final LocalDate endDate,
            final Boolean isPaid
    ) {
        final List<ExcursionGroup> excursionGroupList = excursionGroupRepository
                .findAllByStartDateBetweenAndStatusCompletedAndIsPaid(startDate, endDate, isPaid);

        return excursionGroupList.stream()
                .map(excursionGroupMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExcursionGroupResponseDto> findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmitted(
            final LocalDate startDate,
            final LocalDate endDate,
            final Boolean isDocumentsSubmitted
    ) {
        final List<ExcursionGroup> excursionGroupList = excursionGroupRepository
                .findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmitted(startDate, endDate, isDocumentsSubmitted);

        return excursionGroupList.stream()
                .map(excursionGroupMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByStatusAndStartDateBetween(
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return excursionGroupRepository.countByStatusAndStartDateBetween(status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return excursionGroupRepository.countByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal sumPriceByStatusAndStartDateBetween(
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return excursionGroupRepository.sumPriceByStatusAndStartDateBetween(status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal sumPriceByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return excursionGroupRepository.sumPriceByIsLegalAndStatusAndStartDateBetween(isLegal, status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!organizationRepository.existsById(organizationId)) {
            throw new NotFoundException("Organization not found: " + organizationId);
        }

        return excursionGroupRepository
                .countByOrganizationIdAndStatusAndStartDateBetween(organizationId, status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal sumPriceByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!organizationRepository.existsById(organizationId)) {
            throw new NotFoundException("Organization not found: " + organizationId);
        }

        return excursionGroupRepository
                .sumPriceByOrganizationIdAndStatusAndStartDateBetween(organizationId, status, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByDriverIdAndStatusCompletedAndStartDateBetween(
            final Long driverId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!driverRepository.existsById(driverId)) {
            throw new NotFoundException("Driver not found: " + driverId);
        }

        return excursionGroupRepository
                .countByDriverIdAndStatusCompletedAndStartDateBetween(driverId, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByAccompanyingPersonIdAndStatusCompletedAndStartDateBetween(
            final Long accompanyingPersonId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!accompanyingPersonRepository.existsById(accompanyingPersonId)) {
            throw new NotFoundException("Accompanying person not found: " + accompanyingPersonId);
        }

        return excursionGroupRepository
                .countByAccompanyingPersonIdAndStatusCompletedAndStartDateBetween(accompanyingPersonId, startDate,
                        endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!routeRepository.existsById(routeId)) {
            throw new NotFoundException("Route not found: " + routeId);
        }

        return excursionGroupRepository
                .countByRouteIdAndStatusCompletedAndStartDateBetween(routeId, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal sumPriceByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        if (!routeRepository.existsById(routeId)) {
            throw new NotFoundException("Route not found: " + routeId);
        }

        return excursionGroupRepository
                .sumPriceByRouteIdAndStatusCompletedAndStartDateBetween(routeId, startDate, endDate);
    }

    @Override
    @Transactional
    public ExcursionGroupResponseDto updateExcursionGroupStatus(
            final Long excursionGroupId,
            final ExcursionGroupStatusUpdateRequestDto requestDto
    ) {
        final ExcursionGroup excursionGroup = excursionGroupRepository.findById(excursionGroupId)
                .orElseThrow(() -> new NotFoundException("Excursion group not found: " + excursionGroupId));

        final ExcursionGroupStatus excursionGroupStatus = requestDto.getStatus();

        excursionGroup.setStatus(excursionGroupStatus);

        return excursionGroupMapper.toDto(excursionGroup);
    }

    @Override
    @Transactional
    public ExcursionGroupExpectationResponseDto updateExpectationStatus(
            final Long excursionGroupId,
            final ExcursionGroupExpectationRequestDto requestDto
    ) {
        final ExcursionGroup excursionGroup = excursionGroupRepository.findById(excursionGroupId)
                .orElseThrow(() -> new NotFoundException("Excursion group not found: " + excursionGroupId));

        excursionGroup.setStartDate(requestDto.getStartDate());
        excursionGroup.setEndDate(requestDto.getEndDate());
        excursionGroup.setIsLegal(requestDto.getIsLegal());
        excursionGroup.setContactData(requestDto.getContactData());

        final Long routeId = requestDto.getRouteId();
        final Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new NotFoundException("Route not found: " + routeId));

        excursionGroup.setRoute(route);

        if (requestDto.getIsLegal()) {
            final Long organizationId = requestDto.getOrganizationId();
            final Organization organization = organizationRepository.findById(organizationId)
                    .orElseThrow(() -> new NotFoundException("Organization not found: " + organizationId));

            excursionGroup.setOrganization(organization);
            excursionGroup.setCustomerName(null);
        } else {
            excursionGroup.setCustomerName(requestDto.getCustomerName());
            excursionGroup.setOrganization(null);
        }

        return excursionGroupMapper.toCreateDto(excursionGroup);
    }

    @Override
    @Transactional
    public ExcursionGroupResponseDto updateProcessingStatus(
            final Long excursionGroupId,
            final ExcursionGroupProcessingRequestDto requestDto
    ) {
        final ExcursionGroup excursionGroup = excursionGroupRepository.findById(excursionGroupId)
                .orElseThrow(() -> new NotFoundException("Excursion group not found: " + excursionGroupId));

        final Long accompanyingPersonId = requestDto.getAccompanyingPersonId();

        if (accompanyingPersonId != null) {
            final AccompanyingPerson accompanyingPerson = accompanyingPersonRepository.findById(accompanyingPersonId)
                    .orElseThrow(() -> new NotFoundException("Accompanying person not found: " + accompanyingPersonId));

            excursionGroup.setAccompanyingPerson(accompanyingPerson);
        }

        final Long driverId = requestDto.getDriverId();

        if (driverId != null) {
            final Driver driver = driverRepository.findById(driverId)
                    .orElseThrow(() -> new NotFoundException("Driver not found: " + driverId));

            excursionGroup.setDriver(driver);
        }

        excursionGroup.setPrice(requestDto.getPrice());
        excursionGroup.setExport(requestDto.getExport());
        excursionGroup.setIsPaid(requestDto.getIsPaid());
        excursionGroup.setContractDate(requestDto.getContractDate());
        excursionGroup.setContractNumber(requestDto.getContractNumber());
        excursionGroup.setIsDocumentsSubmitted(requestDto.getIsDocumentsSubmitted());

        return excursionGroupMapper.toDto(excursionGroup);
    }
}