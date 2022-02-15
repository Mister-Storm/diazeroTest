package com.diazero.incidentsapi.domain.usecases;

import com.diazero.incidentsapi.domain.incident.IncidentRepositoryDomain;
import com.diazero.incidentsapi.domain.incident.IncidentRequestCreate;
import com.diazero.incidentsapi.domain.incident.IncidentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class IncidentUseCaseTest {

    @Mock
    private IncidentRepositoryDomain repository;

    @InjectMocks
    IncidentUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateIncident() {
        when(repository);
        IncidentResponse incident = this.useCase.createIncident(this.createRequest());
        assertEquals(incident.idIncident());
    }

    private IncidentRequestCreate createRequest() {
        return new IncidentRequestCreate("incident1", "email service failure");
    }

}