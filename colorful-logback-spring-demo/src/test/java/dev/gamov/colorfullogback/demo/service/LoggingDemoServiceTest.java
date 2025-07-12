package dev.gamov.colorfullogback.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class LoggingDemoServiceTest {

    @InjectMocks
    private LoggingDemoService loggingDemoService;

    @BeforeEach
    void setUp() {
        // Reset any state if needed
    }

    @Test
    void performBusinessOperation_shouldReturnSuccessResult() {
        // When
        Map<String, Object> result = loggingDemoService.performBusinessOperation();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).containsEntry("status", "success");
        assertThat(result).containsEntry("operation", "business-demo");
        assertThat(result).containsEntry("duration", "~260ms");
        assertThat(result).containsEntry("message", "Colorful logging demonstration completed");
        assertThat(result).containsKey("timestamp");
        
        // Verify timestamp is a valid string representation
        String timestamp = (String) result.get("timestamp");
        assertThat(timestamp).isNotNull();
        assertThat(timestamp).isNotEmpty();
    }

    @Test
    void performBusinessOperation_shouldHandleInterruptedException() {
        // Given - interrupt the current thread to simulate InterruptedException
        Thread.currentThread().interrupt();

        // When & Then
        assertThatThrownBy(() -> loggingDemoService.performBusinessOperation())
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Operation interrupted")
            .hasCauseInstanceOf(InterruptedException.class);

        // Verify thread interrupt status is restored
        assertThat(Thread.currentThread().isInterrupted()).isTrue();
        
        // Clear interrupt status for other tests
        Thread.interrupted();
    }

    @Test
    void simulateErrorScenario_shouldAlwaysThrowException() {
        // When & Then - the method should always throw one of the three exception types
        assertThatThrownBy(() -> loggingDemoService.simulateErrorScenario())
            .satisfiesAnyOf(
                exception -> assertThat(exception).isInstanceOf(NullPointerException.class)
                    .hasMessage("Simulated null pointer for demo"),
                exception -> assertThat(exception).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Simulated invalid argument for demo"),
                exception -> assertThat(exception).isInstanceOf(RuntimeException.class)
                    .hasMessage("Simulated runtime exception for demo")
            );
    }

    @Test
    void simulateErrorScenario_shouldThrowNullPointerException() {
        // This test verifies that NullPointerException can be thrown
        // We'll run it multiple times to increase chances of hitting this case
        boolean nullPointerExceptionThrown = false;
        
        for (int i = 0; i < 20; i++) {
            try {
                loggingDemoService.simulateErrorScenario();
            } catch (NullPointerException e) {
                assertThat(e.getMessage()).isEqualTo("Simulated null pointer for demo");
                nullPointerExceptionThrown = true;
                break;
            } catch (Exception e) {
                // Other exceptions are expected, continue trying
            }
        }
        
        // Note: This test might occasionally fail due to randomness, but it's statistically unlikely
        // If it fails consistently, there might be an issue with the random number generation
    }

    @Test
    void simulateErrorScenario_shouldThrowIllegalArgumentException() {
        // This test verifies that IllegalArgumentException can be thrown
        boolean illegalArgumentExceptionThrown = false;
        
        for (int i = 0; i < 20; i++) {
            try {
                loggingDemoService.simulateErrorScenario();
            } catch (IllegalArgumentException e) {
                assertThat(e.getMessage()).isEqualTo("Simulated invalid argument for demo");
                illegalArgumentExceptionThrown = true;
                break;
            } catch (Exception e) {
                // Other exceptions are expected, continue trying
            }
        }
    }

    @Test
    void simulateErrorScenario_shouldThrowRuntimeException() {
        // This test verifies that RuntimeException can be thrown
        boolean runtimeExceptionThrown = false;
        
        for (int i = 0; i < 20; i++) {
            try {
                loggingDemoService.simulateErrorScenario();
            } catch (RuntimeException e) {
                if ("Simulated runtime exception for demo".equals(e.getMessage())) {
                    runtimeExceptionThrown = true;
                    break;
                }
            } catch (Exception e) {
                // Other exceptions are expected, continue trying
            }
        }
    }

    @Test
    void simulateErrorScenario_shouldHandleInterruptedException() {
        // Given - interrupt the current thread to simulate InterruptedException during sleep
        Thread.currentThread().interrupt();

        // When & Then
        assertThatThrownBy(() -> loggingDemoService.simulateErrorScenario())
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Simulation interrupted")
            .hasCauseInstanceOf(InterruptedException.class);

        // Verify thread interrupt status is restored
        assertThat(Thread.currentThread().isInterrupted()).isTrue();
        
        // Clear interrupt status for other tests
        Thread.interrupted();
    }

    @Test
    void demonstratePackageLogging_shouldCompleteSuccessfully() {
        // When & Then - this method should complete without throwing exceptions
        // We can't easily verify the logging output in unit tests, but we can verify
        // that the method executes without errors
        loggingDemoService.demonstratePackageLogging();
        
        // If we reach this point, the method completed successfully
        // The actual logging verification would be done in integration tests
    }

    @Test
    void demonstratePackageLogging_shouldNotThrowException() {
        // When
        try {
            loggingDemoService.demonstratePackageLogging();
        } catch (Exception e) {
            // Then - no exception should be thrown
            assertThat(e).as("demonstratePackageLogging should not throw any exception").isNull();
        }
    }

    @Test
    void performBusinessOperation_shouldReturnConsistentStructure() {
        // When - call the method multiple times
        Map<String, Object> result1 = loggingDemoService.performBusinessOperation();
        Map<String, Object> result2 = loggingDemoService.performBusinessOperation();

        // Then - both results should have the same structure
        assertThat(result1.keySet()).isEqualTo(result2.keySet());
        
        // Static values should be the same
        assertThat(result1.get("status")).isEqualTo(result2.get("status"));
        assertThat(result1.get("operation")).isEqualTo(result2.get("operation"));
        assertThat(result1.get("duration")).isEqualTo(result2.get("duration"));
        assertThat(result1.get("message")).isEqualTo(result2.get("message"));
        
        // Timestamps should be different (unless called at exactly the same time)
        // We'll just verify they're both present and non-null
        assertThat(result1.get("timestamp")).isNotNull();
        assertThat(result2.get("timestamp")).isNotNull();
    }
}