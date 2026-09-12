package org.zapovednik.excursionservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRequestDto {

    @NotEmpty(message = "Organization name is required")
    @Size(max = 50, message = "Organization name must not be exceed 50 characters")
    private String organizationName;

    @NotEmpty(message = "Founder name is required")
    @Size(max = 50, message = "Founder name must not be exceed 50 characters")
    private String founderName;

    @NotEmpty(message = "Registered address is required")
    @Size(max = 512, message = "Registered address must not be exceed 512 characters")
    private String registeredAddress;

    @NotEmpty(message = "Current account is required")
    @Size(min = 28, max = 28, message = "Current account must be exactly 28 digits")
    private String currentAccount;

    @NotNull(message = "Bank id is required")
    private Long bankId;
}