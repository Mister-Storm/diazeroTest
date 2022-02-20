package com.diazero.incidentsapi.infra.api.controller;

import com.diazero.incidentsapi.domain.incident.IncidentResponse;
import com.diazero.incidentsapi.domain.usecases.IncidentNotFoundException;
import com.diazero.incidentsapi.infra.api.exceptionhandler.RestResponseEntityExceptionHandler;
import com.diazero.incidentsapi.infra.service.IncidentService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@AutoConfigureWebTestClient
class IncidentControllerTest {


    public static final String INVALID_REQUEST_FOR_CREATE = "{\"name\": \"\", \"description\":\"978-0-13-468599-1 \"}";
    public static final String VALID_REQUEST_FOR_CREATE = "{\"name\": \"a name\", \"description\":\"978-0-13-468599-1 \"}";
    public static final String INVALID_REQUEST_FOR_UPDATE = "{\"name\": \"\", \"description\":\"978-0-13-468599-1 \"}";
    public static final String VALID_REQUEST_FOR_UPDATE = "{\"idIncident\": \"1\",\"name\": \"a name\", \"description\":\"978-0-13-468599-1 \"}";

    @Mock
    private IncidentService incidentService;

    private MockMvc mockMvc;

    @InjectMocks
    IncidentController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        this.mockMvc =  MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldReturnStatusCode200WhenFindAll() {

        Mockito.when(incidentService.findAllIncidents())
                .thenReturn(List.of(this.createIncidentResponse(), this.createIncidentResponse()));
        RestAssuredMockMvc
                .get("/incidents")
                .then()
                .status(HttpStatus.OK)
                .body("$.size()", Matchers.equalTo(2))
                .body("[0].idIncident", Matchers.equalTo("1"));
    }


    @Test
    public void shouldReturnStatusCode200WhenFindByIdAndExistsIncident() {
        Mockito.when(incidentService.findIncidentById(anyString()))
                .thenReturn(this.createIncidentResponse());
        RestAssuredMockMvc
                .given()
                .contentType(ContentType.JSON)
                .get("/incidents/1")
                .then()
                .status(HttpStatus.OK)
                .body("idIncident", Matchers.equalTo("1"))
                .body("name", Matchers.equalTo("An incident"))
                .body("description", Matchers.equalTo("any description"));
    }

    @Test
    public void shouldReturnStatusCode404WhenFindByIdAndNotExistsIncident() {
        Mockito.when(incidentService.findIncidentById(any()))
                .thenThrow(new IncidentNotFoundException("Incident 1 not found."));
        RestAssuredMockMvc.get("/incidents/1")
                .then()
                .status(HttpStatus.NOT_FOUND)
                .body("message", Matchers.equalTo("Incident 1 not found."));
    }

    @Test
    public void shouldReturnStatusCode400WhenCreateAnInvalidIncident() {
        RestAssuredMockMvc.given()
                .body(INVALID_REQUEST_FOR_CREATE)
                .contentType(ContentType.JSON)
                .post("/incidents")
                .then()
                .status(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnStatusCode201WhenCreateAnValidIncident() {
        Mockito.when(incidentService.createIncident(any()))
                .thenReturn(createIncidentResponse());
        RestAssuredMockMvc.given()
                .body(VALID_REQUEST_FOR_CREATE)
                .contentType(ContentType.JSON)
                .post("/incidents")
                .then()
                .status(HttpStatus.CREATED);
    }

    @Test
    public void shouldUpdateAnEventAndReturn200(){
        Mockito.when(incidentService.updateIncident(any()))
                .thenReturn(createIncidentResponse());
        RestAssuredMockMvc.given()
                .body(VALID_REQUEST_FOR_UPDATE)
                .contentType(ContentType.JSON)
                .patch("/incidents")
                .then()
                .status(HttpStatus.OK);
    }

    @Test
    public void shouldntUpdateAnEventWhenJsonInvalid(){
        RestAssuredMockMvc.given()
                .body(INVALID_REQUEST_FOR_UPDATE)
                .contentType(ContentType.JSON)
                .patch("/incidents")
                .then()
                .status(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldntUpdateAnEventWhenNotExists(){
        Mockito.when(incidentService.updateIncident(any()))
                .thenThrow(new IncidentNotFoundException("Incident 1 not found."));
        RestAssuredMockMvc.given()
                .body(VALID_REQUEST_FOR_UPDATE)
                .contentType(ContentType.JSON)
                .patch("/incidents")
                .then()
                .status(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldDeleteAnExistentIncident() {
        Mockito.doNothing()
                .when(incidentService).deleteIncident(any());
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .delete("/incidents/1")
                .then()
                .status(HttpStatus.NO_CONTENT);
    }

    @Test
    public void shouldntDeleteAnInexistentIncident() {
                Mockito.doThrow(new IncidentNotFoundException("Incident 1 not found."))
                        .when(incidentService).deleteIncident(any());
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .delete("/incidents/1")
                .then()
                .status(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldCloseAnExistentIncident() {
        Mockito.when(incidentService.closeIncident(any())).thenReturn(this.createIncidentResponse());
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .patch("/incidents/1/close")
                .then()
                .status(HttpStatus.OK);
    }

    @Test
    public void shouldntCloseAnInexistentIncident() {
        Mockito.doThrow(new IncidentNotFoundException("Incident 1 not found."))
                .when(incidentService).closeIncident(any());
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .patch("/incidents/1/close")
                .then()
                .status(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldReopenAnExistentIncident() {
        Mockito.when(incidentService.reopenIncident(any())).thenReturn(this.createIncidentResponse());
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .patch("/incidents/1/reopen")
                .then()
                .status(HttpStatus.OK);
    }

    @Test
    public void shouldntReopenAnInexistentIncident() {
        Mockito.doThrow(new IncidentNotFoundException("Incident 1 not found."))
                .when(incidentService).reopenIncident(any());
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .patch("/incidents/1/reopen")
                .then()
                .status(HttpStatus.NOT_FOUND);
    }

    private IncidentResponse createIncidentResponse() {
        return new IncidentResponse("1", "An incident", "any description", null, null, null, null);
    }

}