package com.diazero.incidentsapi.domain.incident;

import java.util.List;
import java.util.Optional;

public interface IncidentRepositoryDomain {

    Incident save(Incident incident);

    List<Incident> findIncidents();

    Optional<Incident> findIncident(String idIncident);

    void deleteIncident(Incident incident);

}
