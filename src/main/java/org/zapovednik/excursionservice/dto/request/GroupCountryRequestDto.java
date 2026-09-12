package org.zapovednik.excursionservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupCountryRequestDto {

    @NotNull(message = "Country id is required")
    private Long countryId;

    @NotNull(message = "Participant quantity is required")
    @Min(value = 1, message = "Participant quantity must be at least 1")
    private Integer participantQuantity;
}