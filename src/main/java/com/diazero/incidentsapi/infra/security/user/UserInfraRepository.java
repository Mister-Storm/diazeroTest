package com.diazero.incidentsapi.infra.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfraRepository extends JpaRepository<UserInfra, Long> {

    boolean existsByUserName(String userName);

    Optional<UserInfra> findByUserName(String userName);

}
