package com.diazero.incidentsapi.domain.usecases;

import com.diazero.incidentsapi.domain.incident.*;

import java.util.List;

public class IncidentUseCase {

    private final IncidentRepositoryDomain repository;


    public IncidentUseCase(IncidentRepositoryDomain repository) {
        this.repository = repository;
    }

    List<IncidentResponse> findAllIncidents() {
        return repository.findIncidents().stream().map(IncidentResponse::anIncidentResponse).toList();
    }

    

    IncidentResponse createIncident(IncidentRequestCreate request) {
        return IncidentResponse.anIncidentResponse(repository.save(request.toIncident()));
    }

    IncidentResponse updateIncident(IncidentRequestUpdate request) {
        Incident incident = repository.findIncident(request.idIncident()).orElseThrow();
        incident.updateFields(request);
        return IncidentResponse.anIncidentResponse(repository.save(incident));
    }




}
