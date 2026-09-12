package org.zapovednik.excursionservice.service;

import java.util.List;
import org.zapovednik.excursionservice.dto.request.BankRequestDto;
import org.zapovednik.excursionservice.dto.response.BankResponseDto;

public interface BankService {
    Long save(final BankRequestDto requestDto);
    BankResponseDto findById(final Long bankId);
    List<BankResponseDto> findAll();
    BankResponseDto updateById(final Long bankId, final BankRequestDto requestDto);
    void deleteById(final Long bankId);
}