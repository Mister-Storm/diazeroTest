package com.diazero.incidentsapi.domain.incident;

import java.time.LocalDateTime;

public class Incident {

    private String idIncident;
    private String name;
    private String description;
    private final LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;
    private Boolean active;

    Incident(){
        this(LocalDateTime.now());
    }
    Incident(LocalDateTime createAt){
        this.active = Boolean.TRUE;
        this.createAt= LocalDateTime.now();
    }

    Incident(LocalDateTime createAt, LocalDateTime updatedAt) {
        this(createAt);
        this.updatedAt = updatedAt;
    }

    Incident(LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime closedAt) {
        this(createAt, updatedAt);
        if(closedAt != null) {
            this.close(closedAt);
        }
    }

    public void close(LocalDateTime closedAt) {
        this.closedAt = closedAt;
        this.active = Boolean.FALSE;
    }

    public void reOpen() {
        this.closedAt = null;
        this.active = Boolean.TRUE;
    }

    public Boolean isOpen() {
        return this.active;
    }

    public String getIdIncident() {
        return idIncident;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    void setName(String name) {
        this.name = name;
    }
    void setDescription(String description) {
        this.description = description;
    }

    void setIdIncident(String idIncident) {
        this.idIncident = idIncident;

    }

}
