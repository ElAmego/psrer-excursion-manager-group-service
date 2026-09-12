package org.zapovednik.excursionservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_countries")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupCountry extends BaseEntity {

    @JoinColumn(name = "group_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ExcursionGroup excursionGroup;

    @JoinColumn(name = "country_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @Column(name = "participant_quantity", nullable = false)
    private Integer participantQuantity;
}