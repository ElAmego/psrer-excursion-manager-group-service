package org.zapovednik.excursionservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.excursionservice.dto.request.BankRequestDto;
import org.zapovednik.excursionservice.dto.response.BankResponseDto;
import org.zapovednik.excursionservice.model.entity.Bank;

@Mapper(componentModel = "spring")
public interface BankMapper {
    BankResponseDto toDto(final Bank bank);
    Bank toEntity(final BankRequestDto request);
}