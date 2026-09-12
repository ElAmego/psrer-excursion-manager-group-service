package org.zapovednik.excursionservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccompanyingPersonResponseDto {
    private Long id;
    private String accompanyingPersonName;
    private String accompanyingPersonPhoneNumber;
}