package dev.gamov.colorfullogback.demo.controller;

import dev.gamov.colorfullogback.demo.service.LoggingDemoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DemoControllerTest {

    @Mock
    private LoggingDemoService loggingDemoService;

    @InjectMocks
    private DemoController demoController;

    private Map<String, Object> mockBusinessResult;

    @BeforeEach
    void setUp() {
        mockBusinessResult = Map.of(
            "status", "success",
            "timestamp", "2025-07-11T22:52:00",
            "operation", "business-demo",
            "duration", "~260ms",
            "message", "Colorful logging demonstration completed"
        );
    }

    @Test
    void demonstrateLogging_shouldReturnSuccessResponse_whenServiceSucceeds() {
        // Given
        when(loggingDemoService.performBusinessOperation()).thenReturn(mockBusinessResult);

        // When
        ResponseEntity<Map<String, Object>> response = demoController.demonstrateLogging();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(mockBusinessResult);
        verify(loggingDemoService).performBusinessOperation();
    }

    @Test
    void demonstrateLogging_shouldReturnErrorResponse_whenServiceThrowsException() {
        // Given
        RuntimeException exception = new RuntimeException("Service error");
        when(loggingDemoService.performBusinessOperation()).thenThrow(exception);

        // When
        ResponseEntity<Map<String, Object>> response = demoController.demonstrateLogging();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).containsEntry("error", "Internal server error");
        assertThat(response.getBody()).containsEntry("message", "Service error");
        verify(loggingDemoService).performBusinessOperation();
    }

    @Test
    void simulateError_shouldReturnBadRequest_whenServiceThrowsException() {
        // Given
        RuntimeException exception = new RuntimeException("Simulated error");
        doThrow(exception).when(loggingDemoService).simulateErrorScenario();

        // When
        ResponseEntity<Map<String, Object>> response = demoController.simulateError();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsEntry("error", "Simulated error");
        assertThat(response.getBody()).containsEntry("message", "Simulated error");
        verify(loggingDemoService).simulateErrorScenario();
    }

    @Test
    void simulateError_shouldReturnOk_whenServiceDoesNotThrowException() {
        // Given
        doNothing().when(loggingDemoService).simulateErrorScenario();

        // When
        ResponseEntity<Map<String, Object>> response = demoController.simulateError();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsEntry("message", "This shouldn't happen");
        verify(loggingDemoService).simulateErrorScenario();
    }

    @Test
    void demonstrateAllLevels_shouldReturnSuccessResponse_withLogLevelInformation() {
        // When
        ResponseEntity<Map<String, Object>> response = demoController.demonstrateAllLevels();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsEntry("message", "All log levels demonstrated");
        assertThat(response.getBody()).containsKey("levels");
        assertThat(response.getBody()).containsKey("colors");
        
        String[] expectedLevels = {"TRACE", "DEBUG", "INFO", "WARN", "ERROR"};
        String[] expectedColors = {"MAGENTA", "BLUE", "GREEN", "YELLOW", "RED"};
        
        assertThat(response.getBody().get("levels")).isEqualTo(expectedLevels);
        assertThat(response.getBody().get("colors")).isEqualTo(expectedColors);
        
        // This endpoint doesn't call the service, so no service interaction should occur
        verifyNoInteractions(loggingDemoService);
    }

    @Test
    void demonstrateAllLevels_shouldNotInteractWithService() {
        // When
        demoController.demonstrateAllLevels();

        // Then
        verifyNoInteractions(loggingDemoService);
    }
}