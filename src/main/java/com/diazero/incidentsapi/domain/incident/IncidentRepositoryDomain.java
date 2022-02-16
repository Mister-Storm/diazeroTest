package com.diazero.incidentsapi.domain.incident;

import java.util.List;

public interface IncidentRepositoryDomain {

    Incident save(Incident incident);

    List<Incident> findIncidents();

    Incident updateFields(Incident incident);


}
