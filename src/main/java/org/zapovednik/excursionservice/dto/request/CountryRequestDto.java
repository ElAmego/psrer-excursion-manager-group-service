package org.zapovednik.excursionservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequestDto {

    @NotEmpty(message = "Two letter country code is required")
    @Size(min = 2, max = 2, message = "Two letter country code must be exactly 2 characters")
    private String twoLetterCountryCode;

    @NotEmpty(message = "Country name is required")
    @Size(max = 50, message = "Country name must not be exceed 50 characters")
    private String countryName;
}