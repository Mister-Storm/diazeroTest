package com.diazero.incidentsapi.domain.usecases;

import com.diazero.incidentsapi.domain.incident.Incident;
import com.diazero.incidentsapi.domain.incident.IncidentRepositoryDomain;
import com.diazero.incidentsapi.domain.incident.IncidentRequestCreate;
import com.diazero.incidentsapi.domain.incident.IncidentResponse;

public class IncidentUseCase {

    private final IncidentRepositoryDomain repository;


    public IncidentUseCase(IncidentRepositoryDomain repository) {
        this.repository = repository;
    }

    IncidentResponse createIncident(IncidentRequestCreate request) {
        Incident incident =

    }

}
