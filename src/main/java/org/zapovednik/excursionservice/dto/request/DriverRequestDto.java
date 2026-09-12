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
public class DriverRequestDto {

    @NotEmpty(message = "Driver name is required")
    @Size(max = 50, message = "Driver name must not exceed 50 characters")
    private String driverName;

    @NotEmpty(message = "Driver phone number is required")
    @Size(min = 9, max = 9, message = "Driver phone number must be exactly 9 characters")
    @Pattern(regexp = "^[0-9]{9}$", message = "Driver phone number must contain exactly 9 digits")
    private String driverPhoneNumber;
}