package org.zapovednik.excursionservice.service;

import java.util.List;
import org.zapovednik.excursionservice.dto.request.CountryRequestDto;
import org.zapovednik.excursionservice.dto.response.CountryResponseDto;

public interface CountryService {
    Long save(final CountryRequestDto requestDto);
    CountryResponseDto findById(final Long countryId);
    List<CountryResponseDto> findAll();
    CountryResponseDto updateById(final Long countryId, final CountryRequestDto requestDto);
    void deleteById(final Long countryId);
}