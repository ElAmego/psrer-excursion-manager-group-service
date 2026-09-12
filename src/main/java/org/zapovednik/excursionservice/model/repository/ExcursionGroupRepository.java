package org.zapovednik.excursionservice.model.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zapovednik.excursionservice.model.entity.ExcursionGroup;
import org.zapovednik.excursionservice.model.entity.type.ExcursionGroupStatus;
import org.zapovednik.excursionservice.model.repository.query.ExcursionGroupQuery;

public interface ExcursionGroupRepository extends JpaRepository<ExcursionGroup, Long> {
    List<ExcursionGroup> findAllByStatusIn(
            final List<ExcursionGroupStatus> statuses
    );

    List<ExcursionGroup> findAllByStartDateBetweenAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final List<ExcursionGroupStatus> statuses
    );

    List<ExcursionGroup> findAllByStartDateBetweenAndOrganizationIdAndStatusIn(
            final LocalDate startDate,
            final LocalDate endDate,
            final Long organizationId,
            final List<ExcursionGroupStatus> statuses
    );


    List<ExcursionGroup> findAllByStartDateBetweenAndStatusCompletedAndIsPaid(
            final LocalDate startDate,
            final LocalDate endDate,
            final Boolean isPaid
    );

    List<ExcursionGroup> findAllByStartDateBetweenAndStatusCompletedAndIsDocumentsSubmitted(
            final LocalDate startDate,
            final LocalDate endDate,
            final Boolean isDocumentsSubmitted
    );

    // --------------------------------- Статистика ---------------------------------

    // Статистика общая: кол-во групп принято или отменено
    Long countByStatusAndStartDateBetween(
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика общая: кол-во групп в рамках физ. или юр. лиц принято или отменено кол-во групп
    Long countByIsLegalAndStatusAndStartDateBetween(
            final Boolean isLegal,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика общая: получено или потеряно денег
    @Query(value = ExcursionGroupQuery.SUM_PRICE_BY_STATUS_AND_START_DATE_BETWEEN)
    BigDecimal sumPriceByStatusAndStartDateBetween(
            @Param("status") final ExcursionGroupStatus status,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );

    // Статистика общая: получено или потеряно денег в рамках физ. или юр. лиц
    @Query(value = ExcursionGroupQuery.SUM_PRICE_BY_IS_LEGAL_AND_STATUS_AND_START_DATE_BETWEEN)
    BigDecimal sumPriceByIsLegalAndStatusAndStartDateBetween(
            @Param("isLegal") final Boolean isLegal,
            @Param("status") final ExcursionGroupStatus status,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );

    // Статистика по юр. лицам индивидуальная: принято или отменено кол-во групп
    Long countByOrganizationIdAndStatusAndStartDateBetween(
            final Long organizationId,
            final ExcursionGroupStatus status,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика по юр. лицам индивидуальная: получено или потеряно денег
    @Query(value = ExcursionGroupQuery.SUM_PRICE_BY_ORGANIZATION_ID_AND_STATUS_AND_START_DATE_BETWEEN)
    BigDecimal sumPriceByOrganizationIdAndStatusAndStartDateBetween(
            @Param("organizationId") final Long organizationId,
            @Param("status") final ExcursionGroupStatus status,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );

    // Статистика по водителям: кол-во групп откатали (status -> Completed)
    Long countByDriverIdAndStatusCompletedAndStartDateBetween(
            final Long driverId,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика по сопровождающим: кол-во групп было сопровождено (status -> Completed)
    Long countByAccompanyingPersonIdAndStatusCompletedAndStartDateBetween(
            final Long accompanyingPersonId,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика по маршрутам: кол-во групп было принято (status -> Completed)
    Long countByRouteIdAndStatusCompletedAndStartDateBetween(
            final Long routeId,
            final LocalDate startDate,
            final LocalDate endDate
    );

    // Статистика по маршрутам: получено денег (status -> Completed)
    @Query(value = ExcursionGroupQuery.SUM_PRICE_BY_ROUTE_ID_AND_STATUS_COMPLETED_AND_START_DATE_BETWEEN)
    BigDecimal sumPriceByRouteIdAndStatusCompletedAndStartDateBetween(
            @Param("routeId") final Long routeId,
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate
    );
}