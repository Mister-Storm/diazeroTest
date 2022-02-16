package com.diazero.incidentsapi.domain.incident;

import java.time.LocalDateTime;

public record IncidentResponse(String idIncident, String name,
                               String description, LocalDateTime createAt,
                               LocalDateTime updatedAt, LocalDateTime closedAt, Boolean active) {

    public static IncidentResponse anIncidentResponse(Incident incident) {
        return new IncidentResponse(incident.getIdIncident(), incident.getName(),
                incident.getDescription(), incident.getCreateAt(), incident.getUpdatedAt(),
                incident.getClosedAt(), incident.isOpen());
    }
}
