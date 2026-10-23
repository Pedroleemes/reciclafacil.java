package br.com.fiap.reciclafacil.bdd;

import br.com.fiap.reciclafacil.ReciclafacilApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@CucumberContextConfiguration
@SpringBootTest(
    classes = ReciclafacilApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Import(ApiTestSecurityConfiguration.class)
public class CucumberSpringConfiguration {
}