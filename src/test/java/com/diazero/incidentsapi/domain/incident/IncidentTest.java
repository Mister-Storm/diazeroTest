package com.diazero.incidentsapi.domain.incident;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class IncidentTest {

    public static final LocalDateTime CLOSING_DATE = LocalDateTime.of(2022, 03, 23, 1, 00);

    @Test
    public void shouldCloseEvent() {

        Incident incident = IncidentBuilder.anIncidentOpenedNow().build();
        incident.close(CLOSING_DATE);
        assertFalse(incident.isOpen());
        assertEquals(CLOSING_DATE, incident.getClosedAt());

    }
}