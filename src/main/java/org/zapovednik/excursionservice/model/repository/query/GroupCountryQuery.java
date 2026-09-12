package org.zapovednik.excursionservice.model.repository.query;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GroupCountryQuery {
    public static final String SUM_PARTICIPANT_QUANTITY_BY_STATUS_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(gc.participantQuantity), 0) as totalParticipants
        FROM GroupCountry gc
        JOIN gc.excursionGroup eg
        WHERE eg.status = :status
        AND eg.startDate BETWEEN :startDate AND :endDate
    """;

    public static final String SUM_PARTICIPANT_QUANTITY_BY_IS_LEGAL_AND_STATUS_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(gc.participantQuantity), 0) as totalParticipants
        FROM GroupCountry gc
        JOIN gc.excursionGroup eg
        WHERE eg.isLegal = :isLegal
        AND eg.status = :status
        AND eg.startDate BETWEEN :startDate AND :endDate
    """;

    public static final String FIND_COUNTRY_STATISTICS_BY_STATUS_COMPLETED_AND_START_DATE_BETWEEN = """
        SELECT c.id as countryId, c.code as code, c.countryName as countryName,
        SUM(gc.participantQuantity) as totalParticipants
        FROM GroupCountry gc
        JOIN gc.country c
        JOIN gc.excursionGroup eg
        WHERE eg.status = 'COMPLETED'
        AND eg.startDate BETWEEN :startDate AND :endDate
        GROUP BY c.id, c.code, c.countryName
    """;

    public static final String SUM_PARTICIPANT_QUANTITY_BY_ORGANIZATION_ID_AND_STATUS_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(gc.participantQuantity), 0) as totalParticipants
        FROM GroupCountry gc
        JOIN gc.excursionGroup eg
        WHERE eg.organization.id = :organizationId
        AND eg.status = :status
        AND eg.startDate BETWEEN :startDate AND :endDate
    """;

    public static final String SUM_PARTICIPANT_QUANTITY_BY_ROUTE_ID_AND_STATUS_COMPLETED_AND_START_DATE_BETWEEN = """
        SELECT COALESCE(SUM(gc.participantQuantity), 0) as totalParticipants
        FROM GroupCountry gc
        JOIN gc.excursionGroup eg
        WHERE eg.route.id = :routeId
        AND eg.status = 'COMPLETED'
        AND eg.startDate BETWEEN :startDate AND :endDate
    """;
}