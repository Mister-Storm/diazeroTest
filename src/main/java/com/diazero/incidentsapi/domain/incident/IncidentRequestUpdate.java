package com.diazero.incidentsapi.domain.incident;

import javax.validation.constraints.NotEmpty;

public record IncidentRequestUpdate(@NotEmpty String idIncident, @NotEmpty String name, @NotEmpty String description) {
}
