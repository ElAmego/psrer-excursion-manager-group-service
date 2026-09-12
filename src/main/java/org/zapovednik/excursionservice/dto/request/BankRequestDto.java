package org.zapovednik.excursionservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankRequestDto {

    @NotEmpty(message = "Bank name is required")
    @Size(max = 50, message = "Bank name must not exceed 50 characters")
    private String bankName;

    @NotEmpty(message = "BIC is required")
    @Size(min = 9, max = 9, message = "BIC must be exactly 9 characters")
    private String bic;
}