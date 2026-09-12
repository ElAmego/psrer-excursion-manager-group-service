package org.zapovednik.excursionservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcursionGroupExpectationRequestDto {

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Is legal is required")
    private Boolean isLegal;

    private Long organizationId;

    @Size(max = 50, message = "Customer name must not be exceed 50 characters")
    private String customerName;

    @NotEmpty(message = "Contact data is required")
    @Size(max = 50, message = "Contact data must not be exceed 50 characters")
    private String contactData;

    @NotNull(message = "Route id is required")
    private Long routeId;
}