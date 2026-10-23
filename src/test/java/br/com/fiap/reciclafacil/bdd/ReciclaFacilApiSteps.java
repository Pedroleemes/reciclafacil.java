package br.com.fiap.reciclafacil.bdd;

import br.com.fiap.reciclafacil.model.PontoColeta;
import br.com.fiap.reciclafacil.repository.PontoColetaRepository;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReciclaFacilApiSteps {

    @LocalServerPort
    private int port;

    @Autowired
    private PontoColetaRepository pontoRepository;

    private Response response;
    private Long pontoId;

    @Before
    public void prepararCenario() {
        RestAssured.reset();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        pontoRepository.deleteAll();

        PontoColeta ponto = new PontoColeta();
        ponto.setNome("Ponto de Teste BDD");
        ponto.setEndereco("Rua dos Testes, 100");
        ponto.setTipoMaterial("PLASTICO");
        ponto.setCapacidademaximaKg(500.0);
        ponto.setCapacidadeAtualKg(0.0);

        pontoId = pontoRepository.saveAndFlush(ponto).getId();
    }

    @Given("que a API está disponível")
    public void apiDisponivel() {
        assertTrue(port > 0, "O servidor de teste deve estar iniciado");
    }

    @Given("que existe um ponto de coleta cadastrado")
    public void existePontoCadastrado() {
        assertNotNull(pontoId);
    }
    @When("envio credenciais válidas para o login")
    public void loginValido() {
        response = given()
            .contentType(ContentType.JSON)
            .body(Map.of(
                "email", "usuario@email.com",
                "password", "SenhaSegura123"
            ))
        .when()
            .post("/auth/login");
    }

    @When("envio credenciais inválidas para o login")
    public void loginInvalido() {
        response = given()
            .contentType(ContentType.JSON)
            .body(Map.of(
                "email", "usuario@email.com",
                "password", "SenhaIncorreta"
            ))
        .when()
            .post("/auth/login");
    }

    @When("solicito a lista de pontos de coleta")
    public void listarPontosColeta() {
        response = given()
        .when()
            .get("/pontos-coleta");
    }

    @When("registro um descarte de {double} kg no ponto cadastrado")
    public void registrarDescarteValido(Double peso) {
        response = given()
            .contentType(ContentType.JSON)
            .body(Map.of("pesoKg", peso))
        .when()
            .post("/pontos-coleta/" + pontoId + "/registrar-descarte");
    }

    @When("tento registrar um descarte de {double} kg no ponto cadastrado")
    public void registrarDescarteInvalido(Double peso) {
        response = given()
            .contentType(ContentType.JSON)
            .body(Map.of("pesoKg", peso))
        .when()
            .post("/pontos-coleta/" + pontoId + "/registrar-descarte");
    }

    @When("registro um descarte de {double} kg para um ponto inexistente")
    public void registrarDescartePontoInexistente(Double peso) {
        response = given()
            .contentType(ContentType.JSON)
            .body(Map.of("pesoKg", peso))
        .when()
            .post("/pontos-coleta/999999/registrar-descarte");
    }

    @When("atualizo o agendamento {long} para o status {string}")
    public void atualizarAgendamento(Long id, String status) {
        response = given()
            .contentType("text/plain")
            .body(status)
        .when()
            .put("/agendamento-coleta/" + id);
    }

    @When("cancelo o agendamento {long}")
    public void cancelarAgendamento(Long id) {
        response = given()
        .when()
            .delete("/agendamento-coleta/" + id);
    }

    @Then("o status HTTP deve ser {int}")
    public void validarStatusHttp(Integer status) {
        response.then().statusCode(status);
    }

    @Then("o campo {string} deve estar preenchido")
    public void validarCampoPreenchido(String campo) {
        response.then()
            .body(campo, not(isEmptyOrNullString()));
    }

    @Then("o JSON da resposta deve corresponder ao schema {string}")
    public void validarSchemaJson(String schema) {
        response.then()
            .body(matchesJsonSchemaInClasspath(schema));
    }

    @Then("o corpo da resposta deve conter o texto {string}")
    public void validarTextoResposta(String texto) {
        response.then()
            .body(containsString(texto));
    }

    @Then("a resposta deve conter o ponto de coleta {string}")
    public void validarPontoListado(String nome) {
        response.then()
            .body("[0].nome", equalTo(nome));
    }

    @Then("o objeto de erro deve conter a validação {string} com a mensagem {string}")
    public void validarErroDeCampo(String campo, String mensagem) {
        response.then()
            .body(campo, equalTo(mensagem));
    }
}