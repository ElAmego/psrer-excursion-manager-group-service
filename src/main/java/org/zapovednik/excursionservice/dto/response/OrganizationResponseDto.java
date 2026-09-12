package org.zapovednik.excursionservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationResponseDto {
    private Long id;
    private String organizationName;
    private String founderName;
    private String registeredAddress;
    private String currentAccount;
    private Long bankId;
    private String bankName;
    private String bic;
}