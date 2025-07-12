package dev.gamov.colorfullogback.demo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gamov.colorfullogback.demo.ColorfulLogbackSpringDemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    classes = ColorfulLogbackSpringDemoApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@SpringJUnitConfig
@AutoConfigureWebMvc
class DemoControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void getDemoEndpoint_shouldReturnSuccessResponse() {
        // When
        ResponseEntity<Map> response = restTemplate.getForEntity(
            getBaseUrl() + "/demo", 
            Map.class
        );

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).containsEntry("status", "success");
        assertThat(response.getBody()).containsEntry("operation", "business-demo");
        assertThat(response.getBody()).containsEntry("duration", "~260ms");
        assertThat(response.getBody()).containsEntry("message", "Colorful logging demonstration completed");
        assertThat(response.getBody()).containsKey("timestamp");
    }

    @Test
    void postSimulateErrorEndpoint_shouldReturnBadRequest() {
        // When
        ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/demo/simulate-error", 
            null, 
            Map.class
        );

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).containsEntry("error", "Simulated error");
        assertThat(response.getBody()).containsKey("message");
        
        // Verify the message is one of the expected error messages
        String message = (String) response.getBody().get("message");
        assertThat(message).satisfiesAnyOf(
            msg -> assertThat(msg).isEqualTo("Simulated null pointer for demo"),
            msg -> assertThat(msg).isEqualTo("Simulated invalid argument for demo"),
            msg -> assertThat(msg).isEqualTo("Simulated runtime exception for demo")
        );
    }

    @Test
    void getLevelsEndpoint_shouldReturnLogLevelInformation() {
        // When
        ResponseEntity<Map> response = restTemplate.getForEntity(
            getBaseUrl() + "/demo/levels", 
            Map.class
        );

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).containsEntry("message", "All log levels demonstrated");
        assertThat(response.getBody()).containsKey("levels");
        assertThat(response.getBody()).containsKey("colors");
        
        // Verify the arrays contain the expected values
        Object levels = response.getBody().get("levels");
        Object colors = response.getBody().get("colors");
        
        assertThat(levels).isNotNull();
        assertThat(colors).isNotNull();
        
        // Convert to lists and verify content (JSON deserialization returns ArrayList)
        java.util.List<String> expectedLevels = java.util.Arrays.asList("TRACE", "DEBUG", "INFO", "WARN", "ERROR");
        java.util.List<String> expectedColors = java.util.Arrays.asList("MAGENTA", "BLUE", "GREEN", "YELLOW", "RED");
        
        assertThat(levels).isEqualTo(expectedLevels);
        assertThat(colors).isEqualTo(expectedColors);
    }

    @Test
    void actuatorHealthEndpoint_shouldReturnUp() {
        // When
        ResponseEntity<Map> response = restTemplate.getForEntity(
            getBaseUrl() + "/actuator/health", 
            Map.class
        );

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).containsEntry("status", "UP");
    }

    @Test
    void nonExistentEndpoint_shouldReturn404() {
        // When
        ResponseEntity<Map> response = restTemplate.getForEntity(
            getBaseUrl() + "/demo/nonexistent", 
            Map.class
        );

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getDemoEndpoint_shouldHandleMultipleRequests() {
        // When - make multiple requests to ensure consistency
        ResponseEntity<Map> response1 = restTemplate.getForEntity(getBaseUrl() + "/demo", Map.class);
        ResponseEntity<Map> response2 = restTemplate.getForEntity(getBaseUrl() + "/demo", Map.class);
        ResponseEntity<Map> response3 = restTemplate.getForEntity(getBaseUrl() + "/demo", Map.class);

        // Then - all responses should be successful
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        // All should have the same structure
        assertThat(response1.getBody()).containsEntry("status", "success");
        assertThat(response2.getBody()).containsEntry("status", "success");
        assertThat(response3.getBody()).containsEntry("status", "success");
    }

    @Test
    void postSimulateErrorEndpoint_shouldHandleMultipleRequests() {
        // When - make multiple requests to test different error scenarios
        ResponseEntity<Map> response1 = restTemplate.postForEntity(getBaseUrl() + "/demo/simulate-error", null, Map.class);
        ResponseEntity<Map> response2 = restTemplate.postForEntity(getBaseUrl() + "/demo/simulate-error", null, Map.class);
        ResponseEntity<Map> response3 = restTemplate.postForEntity(getBaseUrl() + "/demo/simulate-error", null, Map.class);

        // Then - all responses should be bad requests (errors)
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        
        // All should have error structure
        assertThat(response1.getBody()).containsEntry("error", "Simulated error");
        assertThat(response2.getBody()).containsEntry("error", "Simulated error");
        assertThat(response3.getBody()).containsEntry("error", "Simulated error");
    }
}