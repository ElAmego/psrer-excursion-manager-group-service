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
@Table(name = "accompanying_persons")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccompanyingPerson extends BaseEntity {

    @Column(name = "accompanying_person_name", unique = true, nullable = false, length = 50)
    private String accompanyingPersonName;

    @Column(name = "accompanying_person_phone_number", unique = true, nullable = false, length = 9)
    private String accompanyingPersonPhoneNumber;
}