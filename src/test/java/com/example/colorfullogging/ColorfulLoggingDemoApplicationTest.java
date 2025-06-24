package com.example.colorfullogging;

import com.example.colorfullogging.controller.LoggingDemoController;
import com.example.colorfullogging.service.LoggingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ColorfulLoggingDemoApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LoggingDemoController loggingDemoController;

    @Autowired
    private LoggingService loggingService;

    @Test
    void contextLoads() {
        // Verify that the Spring context loads successfully
        assertThat(loggingDemoController).isNotNull();
        assertThat(loggingService).isNotNull();
    }

    @Test
    void loggingDemoEndpoint_shouldReturnSuccessResponse() {
        String url = "http://localhost:" + port + "/api/logging/demo";
        
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo("success");
        assertThat(response.getBody().get("message")).isEqualTo("Check your console for colorful logs!");
        assertThat(response.getBody().get("level")).isEqualTo("all");
    }

    @Test
    void loggingDemoEndpoint_withErrorLevel_shouldReturnSuccessResponse() {
        String url = "http://localhost:" + port + "/api/logging/demo?level=error";
        
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo("success");
        assertThat(response.getBody().get("level")).isEqualTo("error");
    }

    @Test
    void loggingDemoEndpoint_withWarnLevel_shouldReturnSuccessResponse() {
        String url = "http://localhost:" + port + "/api/logging/demo?level=warn";
        
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo("success");
        assertThat(response.getBody().get("level")).isEqualTo("warn");
    }

    @Test
    void loggingDemoEndpoint_withInfoLevel_shouldReturnSuccessResponse() {
        String url = "http://localhost:" + port + "/api/logging/demo?level=info";
        
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo("success");
        assertThat(response.getBody().get("level")).isEqualTo("info");
    }

    @Test
    void loggingDemoEndpoint_withDebugLevel_shouldReturnSuccessResponse() {
        String url = "http://localhost:" + port + "/api/logging/demo?level=debug";
        
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo("success");
        assertThat(response.getBody().get("level")).isEqualTo("debug");
    }

    @Test
    void loggingDemoEndpoint_withTraceLevel_shouldReturnSuccessResponse() {
        String url = "http://localhost:" + port + "/api/logging/demo?level=trace";
        
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo("success");
        assertThat(response.getBody().get("level")).isEqualTo("trace");
    }

    @Test
    void healthCheckEndpoint_shouldReturnHealthyStatus() {
        String url = "http://localhost:" + port + "/api/logging/health";
        
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo("healthy");
        assertThat(response.getBody().get("timestamp")).isNotNull();
    }

    @Test
    void healthCheckEndpoint_shouldReturnValidTimestamp() {
        String url = "http://localhost:" + port + "/api/logging/health";
        
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String timestamp = (String) response.getBody().get("timestamp");
        assertThat(timestamp).isNotNull();
        assertThat(timestamp).isNotEmpty();
        
        // Verify timestamp is a valid number
        assertThat(Long.parseLong(timestamp)).isGreaterThan(0);
    }

    @Test
    void loggingDemoEndpoint_withInvalidLevel_shouldStillReturnSuccess() {
        String url = "http://localhost:" + port + "/api/logging/demo?level=invalid";
        
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo("success");
        assertThat(response.getBody().get("level")).isEqualTo("invalid");
    }

    @Test
    void application_shouldStartWithoutErrors() {
        // This test verifies that the application starts up successfully
        // If we reach this point, the application context has loaded without errors
        assertThat(port).isGreaterThan(0);
    }
}