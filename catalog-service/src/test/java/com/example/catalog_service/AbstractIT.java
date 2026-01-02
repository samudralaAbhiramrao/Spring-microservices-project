package com.example.catalog_service;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = RANDOM_PORT) // This line will picks a new port other than the port which is in use
@Import(TestcontainersConfiguration.class)
public abstract class AbstractIT {

    @LocalServerPort
    // This will map the randomly picked port to our varibale port and later used in restassured to connect or mock
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
}
