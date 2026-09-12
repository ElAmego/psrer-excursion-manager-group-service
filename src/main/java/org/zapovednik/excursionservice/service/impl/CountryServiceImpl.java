package org.zapovednik.excursionservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zapovednik.excursionservice.dto.request.CountryRequestDto;
import org.zapovednik.excursionservice.dto.response.CountryResponseDto;
import org.zapovednik.excursionservice.exception.custom.NotFoundException;
import org.zapovednik.excursionservice.mapper.CountryMapper;
import org.zapovednik.excursionservice.model.entity.Country;
import org.zapovednik.excursionservice.model.repository.CountryRepository;
import org.zapovednik.excursionservice.service.CountryService;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    @Transactional
    public Long save(final CountryRequestDto requestDto) {
        final Country country = countryMapper.toEntity(requestDto);
        final Country savedCountry = countryRepository.save(country);

        return savedCountry.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public CountryResponseDto findById(final Long countryId) {
        final Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Country not found: " + countryId));

        return countryMapper.toDto(country);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryResponseDto> findAll() {
        final List<Country> countryList = countryRepository.findAll();

        return countryList.stream()
                .map(countryMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CountryResponseDto updateById(final Long countryId, final CountryRequestDto requestDto) {
        final Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Country not found: " + countryId));

        country.setCountryName(requestDto.getCountryName());
        country.setTwoLetterCountryCode(requestDto.getTwoLetterCountryCode());

        return countryMapper.toDto(country);
    }

    @Override
    @Transactional
    public void deleteById(final Long countryId) {
        if (countryRepository.existsById(countryId)) {
            countryRepository.deleteById(countryId);
        } else {
            throw new NotFoundException("Country not found: " + countryId);
        }
    }
}