package com.diazero.incidentsapi.domain.usecases;

import com.diazero.incidentsapi.domain.incident.*;

import java.time.LocalDateTime;
import java.util.List;

public class IncidentUseCase {

    private final IncidentRepositoryDomain repository;


    public IncidentUseCase(IncidentRepositoryDomain repository) {
        this.repository = repository;
    }

    public List<IncidentResponse> findAllIncidents() {
        return repository.findIncidents().stream().map(IncidentResponse::anIncidentResponse).toList();
    }

    public IncidentResponse findIncidentById(String id) {
        return IncidentResponse.anIncidentResponse(repository.findIncident(id)
                .orElseThrow(() -> new IncidentNotFoundException(
                        String.format("Incident %s not found.", id))));
    }

    public IncidentResponse createIncident(IncidentRequestCreate request) {
        return IncidentResponse.anIncidentResponse(repository.save(request.toIncident()));
    }

    public IncidentResponse updateIncident(IncidentRequestUpdate request) {
        Incident incident = repository.findIncident(request.idIncident())
                .orElseThrow(() -> new IncidentNotFoundException(
                        String.format("Incident %s not found.", request.idIncident())));
        incident.updateFields(request);
        return IncidentResponse.anIncidentResponse(repository.save(incident));
    }

    public IncidentResponse closeIncident(String idIncident) {
        Incident incident = repository.findIncident(idIncident)
                .orElseThrow(() -> new IncidentNotFoundException(
                        String.format("Incident %s not found.", idIncident)));
        incident.close(LocalDateTime.now());
        return IncidentResponse.anIncidentResponse(repository.save(incident));
    }

    public void deleteIncident(String id) {
        this.repository.deleteIncident(repository.findIncident(id)
                .orElseThrow(() -> new IncidentNotFoundException(
                        String.format("Incident %s not found.", id))));
    }

}
