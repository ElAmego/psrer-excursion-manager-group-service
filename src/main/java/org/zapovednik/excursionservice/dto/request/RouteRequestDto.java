package org.zapovednik.excursionservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequestDto {

    @NotEmpty(message = "Route name is required")
    @Size(max = 50, message = "Route name must not be exceed 50 characters")
    private String routeName;

    @NotNull(message = "Route num is required")
    @Min(value = 0, message = "Route num must not be less than 0")
    @Max(value = 50, message = "Route num must not be more than 50")
    private Integer routeNum;
}