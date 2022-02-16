package com.diazero.incidentsapi.infra.service;

import com.diazero.incidentsapi.domain.incident.IncidentRepositoryDomain;
import com.diazero.incidentsapi.domain.usecases.IncidentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentService extends IncidentUseCase {

    @Autowired
    public IncidentService(IncidentRepositoryDomain repository) {
        super(repository);
    }




}
