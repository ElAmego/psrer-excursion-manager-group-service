package org.zapovednik.excursionservice.model.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zapovednik.excursionservice.model.entity.Organization;
import org.zapovednik.excursionservice.model.repository.projection.OrganizationGroupCountProjection;
import org.zapovednik.excursionservice.model.repository.projection.OrganizationParticipantQuantitySumProjection;
import org.zapovednik.excursionservice.model.repository.query.OrganizationQuery;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByOrganizationName(final String organizationName);

    // Статистика по юр. лицам общая: топ3 по кол-ву принятых групп (status -> COMPLETED, isLegal -> true)
    @Query(value = OrganizationQuery.FIND_TOP_3_ORGANIZATIONS_BY_GROUP_COUNT)
    List<OrganizationGroupCountProjection> findTop3OrganizationsByGroupCount(
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate,
            final Pageable pageable
    );

    // Статистика по юр. лицам общая: топ3 по кол-ву человек (status -> COMPLETED, isLegal -> true)
    @Query(value = OrganizationQuery.FIND_TOP_3_ORGANIZATIONS_BY_PARTICIPANT_QUANTITY_SUM)
    List<OrganizationParticipantQuantitySumProjection> findTop3OrganizationsByParticipantQuantitySum(
            @Param("startDate") final LocalDate startDate,
            @Param("endDate") final LocalDate endDate,
            final Pageable pageable
    );
}