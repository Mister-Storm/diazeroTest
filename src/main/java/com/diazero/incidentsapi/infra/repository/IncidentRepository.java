package com.diazero.incidentsapi.infra.repository;

import com.diazero.incidentsapi.domain.incident.IncidentRepositoryDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends IncidentRepositoryDomain, JpaRepository<IncidentInfra, Long> {



}
