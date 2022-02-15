package com.diazero.incidentsapi.domain.incident;

import java.time.LocalDateTime;

public class IncidentBuilder {

    private Incident incident;

    private IncidentBuilder() {
        this.incident = new Incident();
    }
    private IncidentBuilder(LocalDateTime createAt) {
        this.incident = new Incident(createAt);
    }

    public IncidentBuilder(LocalDateTime createAt, LocalDateTime updatedAt) {
        this.incident = new Incident(createAt, updatedAt);
    }

    public IncidentBuilder(LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime closedAt) {
        this.incident = new Incident(createAt, updatedAt, closedAt);
    }

    public static IncidentBuilder anIncidentOpenedNow() {
        return new IncidentBuilder();
    }
    public static IncidentBuilder anIncidentOpenedIn(LocalDateTime createAt) {
        return new IncidentBuilder(createAt);
    }
    public static IncidentBuilder anIncidentOpenedAndUpdateIn(LocalDateTime createAt, LocalDateTime updatedAt) {
        return new IncidentBuilder(createAt, updatedAt);
    }
    public static IncidentBuilder anIncidentClosed(LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime closedAt) {
        return new IncidentBuilder(createAt, updatedAt, closedAt);
    }

    public IncidentBuilder withIdIncident(String idIncident) {
        this.incident.setIdIncident(idIncident);
        return this;
    }
    public IncidentBuilder withName(String name) {
        this.incident.setName(name);
        return this;
    }
    public IncidentBuilder withDescription(String description) {
        this.incident.setDescription(description);
        return this;
    }

    public Incident build() {
        return this.incident;
    }
}
