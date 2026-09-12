package org.zapovednik.excursionservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryResponseDto {
    private Long id;
    private String twoLetterCountryCode;
    private String countryName;
}