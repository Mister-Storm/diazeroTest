package com.diazero.incidentsapi.domain.incident;

import java.util.List;
import java.util.Optional;

public interface IncidentRepositoryDomain {

    Incident save(Incident incident);

    List<Incident> findIncidents();

    Incident updateFields(Incident incident);

    Optional<Incident> findIncident(String idIncident);


}
