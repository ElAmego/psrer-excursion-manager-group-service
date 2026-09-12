package org.zapovednik.excursionservice.model.repository.projection;

public interface CountryStatisticsProjection {
    Long getCountryId();
    String getCode();
    String getCountryName();
    Long getTotalParticipants();
}