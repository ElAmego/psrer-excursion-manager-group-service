package org.zapovednik.excursionservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends BaseEntity {

    @Column(name = "driver_name", unique = true, nullable = false, length = 50)
    private String driverName;

    @Column(name = "driver_phone_number", unique = true, nullable = false, length = 9)
    private String driverPhoneNumber;
}