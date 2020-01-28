package dev.rinaldo.test.integrated;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

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
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        System.setProperty("quarkus.datasource.url", postgresqlContainer.getJdbcUrl());
    }

    @AfterAll
    private static void cleanup() {
        System.clearProperty("quarkus.datasource.url");
    }

    @BeforeEach
    void setup() {
        assumeTrue(postgresqlContainer.isRunning(), "Banco não está no ar.");
    }

    @Test
    @Transactional
    public void dado_FrutasNaBase_quando_BuscarFrutas_entao_DeveRetornar200ComId1() {
        given()
                .when().get("/frutas")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(lessThan(1000L))
                .body("id", hasItems(1, 2, 3, 4, 5, 6, 7))
                .body("nome", hasSize(greaterThanOrEqualTo(7)))
                .body("votos", hasSize(greaterThanOrEqualTo(7)));
    }

}