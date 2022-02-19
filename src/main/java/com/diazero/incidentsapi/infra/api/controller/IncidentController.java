package com.diazero.incidentsapi.infra.api.controller;

import com.diazero.incidentsapi.domain.incident.IncidentRequestCreate;
import com.diazero.incidentsapi.domain.incident.IncidentRequestUpdate;
import com.diazero.incidentsapi.domain.incident.IncidentResponse;
import com.diazero.incidentsapi.infra.service.IncidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    private final IncidentService incidentService;


    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public ResponseEntity<List<IncidentResponse>> findAll() {
        return ResponseEntity.ok(incidentService.findAllIncidents());
    }

    @GetMapping("/{idIncident}")
    public ResponseEntity<IncidentResponse> findById(@PathVariable("idIncident") String idIncident) {
        return ResponseEntity.ok(incidentService.findIncidentById(idIncident));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IncidentResponse> create(@Valid @RequestBody IncidentRequestCreate requestCreate) {
        return new ResponseEntity<>(incidentService.createIncident(requestCreate), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<IncidentResponse> update(@Valid @RequestBody IncidentRequestUpdate requestUpdate) {
        return new ResponseEntity<>(incidentService.updateIncident(requestUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{idIncident}")
    public ResponseEntity<Void> delete(@PathVariable("idIncident") String idIncident) {
        incidentService.deleteIncident(idIncident);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{idIncident}/close")
    public ResponseEntity<IncidentResponse> closeIncident(@PathVariable("idIncident") String idIncident) {
        return new ResponseEntity<>(incidentService.closeIncident(idIncident), HttpStatus.OK);
    }

    @PatchMapping("/{idIncident}/reopen")
    public ResponseEntity<IncidentResponse> reopenIncident(@PathVariable("idIncident") String idIncident) {
        return new ResponseEntity<>(incidentService.reopenIncident(idIncident), HttpStatus.OK);
    }

}
