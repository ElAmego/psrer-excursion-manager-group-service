package org.zapovednik.excursionservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zapovednik.excursionservice.model.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

}