package dev.rinaldo.test.integrated;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@Testcontainers
public class FrutasResourceIT {

    @SuppressWarnings("rawtypes")
    @Container
    private static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer<>()
            .withDatabaseName("frutas")
            .withUsername("quarkus_it")
            .withPassword("quarkus_it")
            .withExposedPorts(5431);

    @BeforeAll
    private static void configure() {
        System.setProperty("quarkus.datasource.url", postgresqlContainer.getJdbcUrl());
    }

    @AfterAll
    private static void cleanup() {
        System.clearProperty("quarkus.datasource.url");
    }

    @BeforeEach
    void setup() {
        assertTrue("Banco não está no ar.", postgresqlContainer.isRunning());
    }

    @Test
    public void dado_FrutasNaBase_quando_BuscarFrutas_entao_DeveRetornar200() {
        given()
                .when().get("/frutas")
                .then()
                .statusCode(200)
                .body("id", Matchers.containsString("1"));
    }

}