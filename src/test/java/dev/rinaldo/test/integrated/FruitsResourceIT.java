package dev.rinaldo.test.integrated;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

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
public class FruitsResourceIT {

    @Container
    private static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer<>()
            .withDatabaseName("fruits")
            .withUsername("foo")
            .withPassword("secret")
            .withExposedPorts(5432);

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
    public void whenGetFruitsMustReturnFruits() {
        given()
                .when().get("/fruits")
                .then()
                .statusCode(200);
    }

}