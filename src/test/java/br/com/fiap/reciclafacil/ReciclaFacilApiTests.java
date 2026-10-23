package br.com.fiap.reciclafacil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReciclaFacilApiTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void deveRetornar200EValidarContratoNoLogin() {
        // Simula o Cenário BDD: Login com sucesso (Caminho Feliz)
        given()
            .contentType(ContentType.JSON)
            .body("{ \"email\": \"usuario@email.com\", \"password\": \"SenhaSegura123\" }") // Ajuste para um usuário válido no seu BD
        .when()
            .post("/auth/login") // Ajuste para a rota correta do seu Controller
        .then()
            .statusCode(200)
            .body("token", notNullValue())
            .body(matchesJsonSchemaInClasspath("login-schema.json"));
    }

    @Test
    public void deveRetornarStatusDeErroParaCredenciaisInvalidas() {
        // Simula o Cenário BDD: Login com credenciais inválidas (Caminho Negativo)
        given()
            .contentType(ContentType.JSON)
            .body("{ \"email\": \"usuario@email.com\", \"password\": \"SenhaIncorreta\" }")
        .when()
            .post("/auth/login")
        .then()
            .statusCode(anyOf(is(401), is(403)));
    }
}