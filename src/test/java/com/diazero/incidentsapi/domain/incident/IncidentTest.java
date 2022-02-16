package com.diazero.incidentsapi.domain.incident;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class IncidentTest {

    public static final LocalDateTime CLOSING_DATE = LocalDateTime.of(2022, 03, 23, 1, 00);
    private static final String ID_INCIDENT = "1";
    private static final String INCIDENT_NAME_UPDATE = "Any";
    private static final String INCIDENT_DESCRIPTION_UPDATE = "Any description";


    @Test
    public void shouldCloseEvent() {
        Incident incident = IncidentBuilder.anIncidentOpenedNow().build();
        incident.close(CLOSING_DATE);
        assertFalse(incident.isOpen());
        assertEquals(CLOSING_DATE, incident.getClosedAt());
    }
    @Test
    public void shouldUpdateEvent() {
        Incident incident = IncidentBuilder.anIncidentOpenedNow().build();
        incident.updateFields(new IncidentRequestUpdate(ID_INCIDENT, INCIDENT_NAME_UPDATE, INCIDENT_DESCRIPTION_UPDATE));
        assertTrue(incident.isOpen());
        assertEquals(INCIDENT_NAME_UPDATE, incident.getName());
        assertEquals(INCIDENT_DESCRIPTION_UPDATE, incident.getDescription());
        assertNotNull(incident.getUpdatedAt());
    }
}