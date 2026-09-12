package org.zapovednik.excursionservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.excursionservice.dto.request.AccompanyingPersonRequestDto;
import org.zapovednik.excursionservice.dto.response.AccompanyingPersonResponseDto;
import org.zapovednik.excursionservice.exception.custom.NotFoundException;
import org.zapovednik.excursionservice.mapper.AccompanyingPersonMapper;
import org.zapovednik.excursionservice.model.entity.AccompanyingPerson;
import org.zapovednik.excursionservice.model.repository.AccompanyingPersonRepository;
import org.zapovednik.excursionservice.service.AccompanyingPersonService;

@Service
@RequiredArgsConstructor
public class AccompanyingPersonServiceImpl implements AccompanyingPersonService {
    private final AccompanyingPersonRepository accompanyingPersonRepository;
    private final AccompanyingPersonMapper accompanyingPersonMapper;

    @Override
    @Transactional
    public Long save(final AccompanyingPersonRequestDto requestDto) {
        final AccompanyingPerson accompanyingPerson = accompanyingPersonMapper.toEntity(requestDto);
        final AccompanyingPerson savedAccompanyingPerson = accompanyingPersonRepository
                .save(accompanyingPerson);

        return savedAccompanyingPerson.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public AccompanyingPersonResponseDto findById(final Long accompanyingPersonId) {
        final AccompanyingPerson accompanyingPerson = accompanyingPersonRepository
                .findById(accompanyingPersonId)
                .orElseThrow(() -> new NotFoundException("AccompanyingPerson not found: " + accompanyingPersonId));

        return accompanyingPersonMapper.toDto(accompanyingPerson);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccompanyingPersonResponseDto> findAll() {
        final List<AccompanyingPerson> accompanyingPersonList = accompanyingPersonRepository.findAll();

        return accompanyingPersonList.stream()
                .map(accompanyingPersonMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public AccompanyingPersonResponseDto updateById(
            final Long accompanyingPersonId,
            final AccompanyingPersonRequestDto requestDto
    ) {
        final AccompanyingPerson accompanyingPerson = accompanyingPersonRepository
                .findById(accompanyingPersonId)
                .orElseThrow(() -> new NotFoundException("AccompanyingPerson not found: " + accompanyingPersonId));

        accompanyingPerson.setAccompanyingPersonName(requestDto.getAccompanyingPersonName());
        accompanyingPerson.setAccompanyingPersonPhoneNumber(requestDto.getAccompanyingPersonPhoneNumber());

        return accompanyingPersonMapper.toDto(accompanyingPerson);
    }

    @Override
    @Transactional
    public void deleteById(final Long accompanyingPersonId) {
        if (accompanyingPersonRepository.existsById(accompanyingPersonId)) {
            accompanyingPersonRepository.deleteById(accompanyingPersonId);
        } else {
            throw new NotFoundException("AccompanyingPerson not found: " + accompanyingPersonId);
        }
    }
}