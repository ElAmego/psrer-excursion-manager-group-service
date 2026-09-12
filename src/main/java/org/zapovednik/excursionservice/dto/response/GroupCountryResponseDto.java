package org.zapovednik.excursionservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupCountryResponseDto {
    private Long id;
    private Long countryId;
    private String twoLetterCountryCode;
    private String countryName;
    private Integer participantQuantity;
}