package org.zapovednik.excursionservice.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zapovednik.excursionservice.model.entity.type.ExcursionGroupStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcursionGroupResponseDto {
    private Long id;
    private ExcursionGroupStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isLegal;
    private String contactData;

    private String customerName;

    private Long organizationId;
    private String organizationName;
    private String founderName;

    private Long driverId;
    private String driverName;

    private Long accompanyingPersonId;
    private String accompanyingPersonName;

    private Long routeId;
    private String routeName;
    private Integer routeNum;

    private List<GroupCountryResponseDto> countries;
}