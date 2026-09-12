package org.zapovednik.excursionservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccompanyingPersonRequestDto {

    @NotEmpty(message = "Accompanying person name is required")
    @Size(max = 50, message = "Accompanying person name must not exceed 50 characters")
    private String accompanyingPersonName;

    @NotEmpty(message = "Accompanying person phone number is required")
    @Size(min = 9, max = 9, message = "Accompanying person phone number must be exactly 9 characters")
    @Pattern(regexp = "^[0-9]{9}$", message = "Accompanying person phone number must contain exactly 12 digits")
    private String accompanyingPersonPhoneNumber;
}