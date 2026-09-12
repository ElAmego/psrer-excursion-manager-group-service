package org.zapovednik.excursionservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zapovednik.excursionservice.model.entity.AccompanyingPerson;

public interface AccompanyingPersonRepository extends JpaRepository<AccompanyingPerson, Long> {

}