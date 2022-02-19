package com.diazero.incidentsapi.domain.usecases;

import com.diazero.incidentsapi.domain.incident.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class IncidentUseCaseTest {

    public static final String INCIDENT_NAME = "incident1";
    public static final String INCIDENT_DESCRIPTION = "email service failure";
    public static final String ID_INCIDENT = "4";
    private static final String INCIDENT_NAME_UPDATE = "incidentA";
    private static final String INCIDENT_DESCRIPTION_UPDATE = "email service failure again";

    @Mock
    private IncidentRepositoryDomain repository;

    @InjectMocks
    IncidentUseCase useCase;

    @Captor
    ArgumentCaptor<Incident> argumentCaptor;

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
        assertTrue(this.createIncidentResponse().active());
    }

    @Test
    public void shouldUpdateFields() {
        when(repository.findIncident(any())).thenReturn(Optional.of(this.createMockIncident()));
        when(repository.save(any())).thenReturn(this.createMockIncidentUpdated());

        this.useCase.updateIncident(this.createRequestUpdate());

        Mockito.verify(repository).save(argumentCaptor.capture());
        Incident incident = argumentCaptor.getValue();
        assertEquals(ID_INCIDENT, incident.getIdIncident());
        assertEquals(INCIDENT_NAME_UPDATE, incident.getName());
        assertEquals(INCIDENT_DESCRIPTION_UPDATE, incident.getDescription());
        assertTrue(incident.isOpen());
        assertNotNull(incident.getUpdatedAt());
    }

    @Test
    public void shouldThrowExceptionWhenUpdateFieldsAndNotFindIncident() {
        when(repository.findIncident(any())).thenReturn(Optional.empty());

        assertThrows(IncidentNotFoundException.class,
                () -> this.useCase.updateIncident(this.createRequestUpdate()));
        Mockito.verify(repository, times(0)).save(any());
    }

    @Test

    public void shouldCloseAnIncident() {
        when(repository.findIncident(any())).thenReturn(Optional.of(this.createMockIncident()));
        when(repository.save(any())).thenReturn(this.createMockIncidentUpdated());

        this.useCase.closeIncident(ID_INCIDENT);

        Mockito.verify(repository).save(argumentCaptor.capture());
        Incident incident = argumentCaptor.getValue();
        assertFalse(incident.isOpen());
        assertNotNull(incident.getClosedAt());
    }

    @Test
    public void shouldThrowExceptionWhenCloseAndNotFindIncident() {
        when(repository.findIncident(any())).thenReturn(Optional.empty());

        assertThrows(IncidentNotFoundException.class,
                () -> this.useCase.closeIncident(ID_INCIDENT));
        Mockito.verify(repository, times(0)).save(any());

    }

    private Incident createMockIncidentUpdated() {
        Incident incident = this.createMockIncident();
        incident.updateFields(this.createRequestUpdate());
        return incident;
    }

    private IncidentRequestUpdate createRequestUpdate() {
        return new IncidentRequestUpdate(ID_INCIDENT, INCIDENT_NAME_UPDATE, INCIDENT_DESCRIPTION_UPDATE);
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