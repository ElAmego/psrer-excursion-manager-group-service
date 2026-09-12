package org.zapovednik.excursionservice.mapper;

import org.mapstruct.Mapper;
import org.zapovednik.excursionservice.dto.request.AccompanyingPersonRequestDto;
import org.zapovednik.excursionservice.dto.response.AccompanyingPersonResponseDto;
import org.zapovednik.excursionservice.model.entity.AccompanyingPerson;

@Mapper(componentModel = "spring")
public interface AccompanyingPersonMapper {
    AccompanyingPersonResponseDto toDto(final AccompanyingPerson accompanyingPerson);
    AccompanyingPerson toEntity(final AccompanyingPersonRequestDto request);
}