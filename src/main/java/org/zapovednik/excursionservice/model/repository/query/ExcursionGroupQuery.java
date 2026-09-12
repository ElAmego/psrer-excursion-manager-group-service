package org.zapovednik.excursionservice.model.repository.query;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExcursionGroupQuery {
    public static final String SUM_PRICE_BY_STATUS_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(eg.price), 0) as totalPrice
        FROM ExcursionGroup eg
        WHERE eg.status = :status
        AND eg.startDate BETWEEN :startDate AND :endDate
    """;

    public static final String SUM_PRICE_BY_IS_LEGAL_AND_STATUS_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(eg.price), 0) as totalPrice
        FROM ExcursionGroup eg
        WHERE eg.isLegal = :isLegal
        AND eg.status = :status
        AND eg.startDate BETWEEN :startDate AND :endDate
    """;

    public static final String SUM_PRICE_BY_ORGANIZATION_ID_AND_STATUS_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(eg.price), 0) as totalPrice
        FROM ExcursionGroup eg
        WHERE eg.organization.id = :organizationId
        AND eg.status = :status
        AND eg.startDate BETWEEN :startDate AND :endDate
    """;

    public static final String SUM_PRICE_BY_ROUTE_ID_AND_STATUS_COMPLETED_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(eg.price), 0) as totalPrice
        FROM ExcursionGroup eg
        WHERE eg.route.id = :routeId
        AND eg.status = 'COMPLETED'
        AND eg.startDate BETWEEN :startDate AND :endDate
    """;
}