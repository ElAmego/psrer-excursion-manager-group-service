package org.zapovednik.excursionservice.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zapovednik.excursionservice.model.entity.type.ExcursionGroupStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcursionGroupExpectationResponseDto {
    private Long id;
    private ExcursionGroupStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isLegal;
    private String contactData;

    private Long routeId;
    private String routeName;
    private Integer routeNum;

    private String customerName;

    private Long organizationId;
    private String organizationName;
    private String founderName;
}