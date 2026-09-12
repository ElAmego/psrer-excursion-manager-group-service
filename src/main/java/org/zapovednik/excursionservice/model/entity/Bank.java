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
@Table(name = "banks")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bank extends BaseEntity {

    @Column(name = "bank_name", unique = true, nullable = false, length = 50)
    private String bankName;

    @Column(name = "bic", unique = true, nullable = false, length = 9)
    private String bic;
}