package com.diazero.incidentsapi.infra.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfraService {

    private final UserInfraRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserInfraService(UserInfraRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public UserInfra create(UserInfra user) {
        if(repository.existsByUserName(user.getUserName())) {
            throw new UserAlreadyExistsException(
                    String.format("The user name %s, already exists.", user.getUserName()));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }


}
