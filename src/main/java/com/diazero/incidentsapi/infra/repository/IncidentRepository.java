package com.diazero.incidentsapi.infra.repository;

import com.diazero.incidentsapi.domain.incident.Incident;
import com.diazero.incidentsapi.domain.incident.IncidentRepositoryDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentRepository extends IncidentRepositoryDomain, JpaRepository<IncidentInfra, Long> {

    Logger log = LoggerFactory.getLogger(IncidentRepository.class);

    @Override
    default List<Incident> findIncidents() {
        List<IncidentInfra> incidents = this.findAll();
        return incidents.stream().map(IncidentInfra::createIncidentDomain).toList();
    }
    @Override
    default Incident save(Incident incident){
        log.info("Saving entity: {}", incident.toString());
        IncidentInfra entity = this.save(IncidentInfra.createIncidentInfra(incident));
        log.info("{} save was sucefull", incident);
        return entity.createIncidentDomain();
    }

    @Override
    default Optional<Incident> findIncident(String idIncident) {
        Optional<IncidentInfra> incidentInfra = this.findById(Long.parseLong(idIncident));
        return incidentInfra.map(IncidentInfra::createIncidentDomain);
    }


    @Override
    default void deleteIncident(Incident incident){
        this.delete(IncidentInfra.createIncidentInfra(incident));
    }


    @Override
    <S extends IncidentInfra> S save(S entity);

    @Override
    List<IncidentInfra> findAll();

}
