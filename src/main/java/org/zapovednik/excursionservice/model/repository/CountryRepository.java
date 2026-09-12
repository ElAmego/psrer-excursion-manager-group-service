package org.zapovednik.excursionservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zapovednik.excursionservice.model.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}