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
@Table(name = "countries")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseEntity {

    @Column(name = "two_letter_country_code", unique = true, nullable = false, length = 2)
    private String twoLetterCountryCode;

    @Column(name = "country_name", unique = true, nullable = false, length = 50)
    private String countryName;
}