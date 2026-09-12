package org.zapovednik.excursionservice.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcursionGroupProcessingRequestDto {
    private Long accompanyingPersonId;
    private Long driverId;

    @DecimalMin(value = "0.00", message = "Price must not be less than 0")
    @Digits(integer = 8, fraction = 2, message = "Price must have up to 8 integer digits and 2 decimal digits")
    private BigDecimal price;

    @DecimalMin(value = "0.00", message = "Export must not be less than 0")
    @Digits(integer = 8, fraction = 2, message = "Export must have up to 8 integer digits and 2 decimal digits")
    private BigDecimal export;

    private Boolean isPaid;
    private LocalDate contractDate;
    private Integer contractNumber;
    private Boolean isDocumentsSubmitted;
}