package com.diazero.incidentsapi.infra.repository;

import com.diazero.incidentsapi.domain.incident.Incident;
import com.diazero.incidentsapi.domain.incident.IncidentBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class IncidentInfra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    private Long idIncident;
    @Access(AccessType.FIELD)
    private String name;
    @Access(AccessType.FIELD)
    private String description;
    @Access(AccessType.FIELD)
    private LocalDateTime createAt;
    @Access(AccessType.FIELD)
    private LocalDateTime updatedAt;
    @Access(AccessType.FIELD)
    private LocalDateTime closedAt;

    public Incident createIncidentDomain() {
        return IncidentBuilder.anIncidentClosed(createAt, updatedAt, closedAt)
                .withIdIncident(idIncident == null ? null : idIncident.toString())
                .withName(name)
                .withDescription(description)
                .build();
    }

    public static IncidentInfra createIncidentInfra(Incident incident) {
        IncidentInfra incidentInfra = new IncidentInfra();
        incidentInfra.idIncident= incident.getIdIncident() == null ? null : Long.parseLong(incident.getIdIncident());
        incidentInfra.name = incident.getName();
        incidentInfra.description = incident.getDescription();
        incidentInfra.createAt = incident.getCreateAt();
        incidentInfra.updatedAt = incident.getUpdatedAt();
        incidentInfra.closedAt = incident.getClosedAt();
        return incidentInfra;
    }
}
