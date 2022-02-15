package com.diazero.incidentsapi.domain.incident;

public record IncidentRequestCreate(String name, String description) {

    public Incident toIncident(){
        return IncidentBuilder.anIncidentOpenedNow()
                .withName(this.name)
                .withDescription(this.description)
                .build();
    }
}
