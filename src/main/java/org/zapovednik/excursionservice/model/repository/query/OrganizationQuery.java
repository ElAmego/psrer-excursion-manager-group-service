package org.zapovednik.excursionservice.model.repository.query;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrganizationQuery {
    public static final String FIND_TOP_3_ORGANIZATIONS_BY_GROUP_COUNT = """
            SELECT o.organizationName as organizationName, COUNT(eg.id) as groupCount
            FROM Organization o
            JOIN ExcursionGroup eg ON eg.organization = o
            WHERE eg.isLegal = true
            AND eg.status = 'COMPLETED'
            AND eg.startDate BETWEEN :startDate AND :endDate
            GROUP BY o.id, o.organizationName
            ORDER BY groupCount DESC
    """;

    public static final String FIND_TOP_3_ORGANIZATIONS_BY_PARTICIPANT_QUANTITY_SUM = """
            SELECT o.organizationName as organizationName, SUM(gc.participantQuantity) as participantQuantitySum
            FROM Organization o
            JOIN ExcursionGroup eg ON eg.organization = o
            JOIN GroupCountry gc ON gc.excursionGroup = eg
            WHERE eg.isLegal = true
            AND eg.status = 'COMPLETED'
            AND eg.startDate BETWEEN :startDate AND :endDate
            GROUP BY o.id, o.organizationName
            ORDER BY participantQuantitySum DESC
    """;
}