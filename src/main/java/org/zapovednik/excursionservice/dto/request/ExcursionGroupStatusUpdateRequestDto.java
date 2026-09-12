package org.zapovednik.excursionservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zapovednik.excursionservice.model.entity.type.ExcursionGroupStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcursionGroupStatusUpdateRequestDto {
    @NotNull(message = "Status is required")
    private ExcursionGroupStatus status;
}