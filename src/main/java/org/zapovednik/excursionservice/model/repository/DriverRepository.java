package org.zapovednik.excursionservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zapovednik.excursionservice.model.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}