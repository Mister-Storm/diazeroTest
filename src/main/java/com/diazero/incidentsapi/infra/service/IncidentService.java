package com.diazero.incidentsapi.infra.service;

import com.diazero.incidentsapi.domain.incident.IncidentRepositoryDomain;
import com.diazero.incidentsapi.domain.usecases.IncidentUseCase;

public class IncidentService extends IncidentUseCase {

    public IncidentService(IncidentRepositoryDomain repository) {
        super(repository);
    }


}
