package com.diazero.incidentsapi.domain.incident;

import java.time.LocalDateTime;

public record IncidentResponse(String idIncident, String name,
                               String description, LocalDateTime createAt,
                               LocalDateTime updatedAt, LocalDateTime closedAt, Boolean active) {
}
