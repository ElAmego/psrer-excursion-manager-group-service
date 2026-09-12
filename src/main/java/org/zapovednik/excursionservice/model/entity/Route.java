package org.zapovednik.excursionservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "routes")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Route extends BaseEntity {

    @Column(name = "route_name", unique = true, nullable = false, length = 50)
    private String routeName;

    @Column(name = "route_num", unique = true, nullable = false)
    @Min(0)
    @Max(50)
    private Integer routeNum;
}