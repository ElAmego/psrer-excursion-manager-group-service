package org.zapovednik.excursionservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zapovednik.excursionservice.model.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

}