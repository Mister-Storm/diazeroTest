package com.diazero.incidentsapi.domain.incident;

import javax.validation.constraints.NotEmpty;

public record IncidentRequestCreate(@NotEmpty String name, @NotEmpty String description) {

    public Incident toIncident(){
        return IncidentBuilder.anIncidentOpenedNow()
                .withName(this.name)
                .withDescription(this.description)
                .build();
    }
}
