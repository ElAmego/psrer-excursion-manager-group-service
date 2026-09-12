package org.zapovednik.excursionservice.service.impl;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.excursionservice.dto.request.OrganizationRequestDto;
import org.zapovednik.excursionservice.dto.response.OrganizationResponseDto;
import org.zapovednik.excursionservice.exception.custom.NotFoundException;
import org.zapovednik.excursionservice.mapper.OrganizationMapper;
import org.zapovednik.excursionservice.model.entity.Bank;
import org.zapovednik.excursionservice.model.entity.Organization;
import org.zapovednik.excursionservice.model.repository.BankRepository;
import org.zapovednik.excursionservice.model.repository.OrganizationRepository;
import org.zapovednik.excursionservice.model.repository.projection.OrganizationGroupCountProjection;
import org.zapovednik.excursionservice.model.repository.projection.OrganizationParticipantQuantitySumProjection;
import org.zapovednik.excursionservice.service.OrganizationService;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final BankRepository bankRepository;
    private final OrganizationMapper organizationMapper;
    private static final Pageable TOP_3 = PageRequest.of(0, 3);

    @Override
    @Transactional
    public Long save(final OrganizationRequestDto requestDto) {
        final Organization organization = organizationMapper.toEntity(requestDto);
        final Long bankId = requestDto.getBankId();
        final Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new NotFoundException("Bank not found: " + bankId));

        organization.setBank(bank);

        final Organization savedOrganization = organizationRepository.save(organization);

        return savedOrganization.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationResponseDto findById(final Long organizationId) {
        final Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found: " + organizationId));

        return organizationMapper.toDto(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationResponseDto> findAll() {
        final List<Organization> organizationList = organizationRepository.findAll();

        return organizationList.stream()
                .map(organizationMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrganizationResponseDto updateById(final Long organizationId, final OrganizationRequestDto requestDto) {
        final Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found: " + organizationId));

        final Long bankId = requestDto.getBankId();

        final Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new NotFoundException("Bank not found: " + bankId));

        organization.setOrganizationName(requestDto.getOrganizationName());
        organization.setFounderName(requestDto.getFounderName());
        organization.setRegisteredAddress(requestDto.getRegisteredAddress());
        organization.setCurrentAccount(requestDto.getCurrentAccount());
        organization.setBank(bank);

        return organizationMapper.toDto(organization);
    }

    @Override
    @Transactional
    public void deleteById(final Long organizationId) {
        if (organizationRepository.existsById(organizationId)) {
            organizationRepository.deleteById(organizationId);
        } else {
            throw new NotFoundException("Organization not found: " + organizationId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationGroupCountProjection> findTop3ByGroupCount(
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return organizationRepository.findTop3OrganizationsByGroupCount(startDate, endDate, TOP_3);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationParticipantQuantitySumProjection> findTop3ByParticipantQuantitySum(
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        return organizationRepository.findTop3OrganizationsByParticipantQuantitySum(startDate, endDate, TOP_3);
    }
}