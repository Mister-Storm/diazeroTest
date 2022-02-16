package com.diazero.incidentsapi.domain.usecases;

import com.diazero.incidentsapi.domain.incident.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class IncidentUseCaseTest {

    public static final String INCIDENT_NAME = "incident1";
    public static final String INCIDENT_DESCRIPTION = "email service failure";
    public static final String ID_INCIDENT = "4";
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
        when(repository.save(any())).thenReturn(this.createMockIncident());
        IncidentResponse incident = this.useCase.createIncident(this.createRequest());
        assertEquals(this.createIncidentResponse().idIncident(), incident.idIncident());
        assertEquals(this.createIncidentResponse().name(), incident.name());
        assertEquals(this.createIncidentResponse().description(), incident.description());
        assertEquals(this.createIncidentResponse().active(), incident.active());
    }

    private Incident createMockIncident() {
        return IncidentBuilder.anIncidentOpenedNow()
                .withName(INCIDENT_NAME)
                .withDescription(INCIDENT_DESCRIPTION)
                .withIdIncident(ID_INCIDENT)
                .build();
    }

    private IncidentResponse createIncidentResponse() {
        return new IncidentResponse(ID_INCIDENT, INCIDENT_NAME, INCIDENT_DESCRIPTION, null, null, null, Boolean.TRUE);
    }

    private IncidentRequestCreate createRequest() {
        return new IncidentRequestCreate(INCIDENT_NAME, INCIDENT_DESCRIPTION);
    }

}