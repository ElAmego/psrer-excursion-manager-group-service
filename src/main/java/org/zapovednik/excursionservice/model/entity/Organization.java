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
@Table(name = "organizations")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization extends BaseEntity {

    @Column(name = "organization_name", unique = true, nullable = false, length = 50)
    private String organizationName;

    @Column(name = "founder_name", nullable = false, length = 50)
    private String founderName;

    @Column(name = "registered_address", unique = true, nullable = false, length = 512)
    private String registeredAddress;

    @Column(name = "current_account", unique = true, nullable = false, length = 28)
    private String currentAccount;

    @JoinColumn(name = "bank_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;
}