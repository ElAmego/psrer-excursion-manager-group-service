package org.zapovednik.excursionservice.service;

import java.util.List;
import org.zapovednik.excursionservice.dto.request.AccompanyingPersonRequestDto;
import org.zapovednik.excursionservice.dto.response.AccompanyingPersonResponseDto;

public interface AccompanyingPersonService {
    Long save(final AccompanyingPersonRequestDto requestDto);
    AccompanyingPersonResponseDto findById(final Long accompanyingPersonId);
    List<AccompanyingPersonResponseDto> findAll();
    AccompanyingPersonResponseDto updateById(final Long accompanyingPersonId, final AccompanyingPersonRequestDto requestDto);
    void deleteById(final Long accompanyingPersonId);
}