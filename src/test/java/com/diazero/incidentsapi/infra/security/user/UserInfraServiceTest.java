package com.diazero.incidentsapi.infra.security.user;

import com.diazero.incidentsapi.domain.incident.Incident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserInfraServiceTest {

    public static final String USER_PASSWORD = "password";
    public static final String USER_NAME = "User name";
    public static final String PASSWORD_ENCRYPTED = "PSSSDFEWAADVGNG";
    @Mock
    private UserInfraRepository repository;
    @Captor
    ArgumentCaptor<UserInfra> argumentCaptor;
    @Mock
    PasswordEncoder encoder;
    @InjectMocks
    private UserInfraService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldCreateNewServer() {
        Mockito.when(repository.existsByUserName(any())).thenReturn(Boolean.FALSE);
        Mockito.when(encoder.encode(any())).thenReturn(PASSWORD_ENCRYPTED);

        this.service.create(this.createUserInfra());
        Mockito.verify(repository).save(argumentCaptor.capture());
        UserInfra user = argumentCaptor.getValue();
        Mockito.verify(repository, Mockito.times(1)).save(any());

        assertEquals(PASSWORD_ENCRYPTED, user.getPassword());
    }

    @Test
    public void shouldThrowExceptionWhenUserAlreadyExists() {
        Mockito.when(repository.existsByUserName(any())).thenReturn(Boolean.TRUE);

        assertThrows(UserAlreadyExistsException.class, () -> this.service.create(this.createUserInfra()));

    }

    private UserInfra createUserInfra() {
        return new UserInfra(USER_NAME, USER_PASSWORD);
    }

}