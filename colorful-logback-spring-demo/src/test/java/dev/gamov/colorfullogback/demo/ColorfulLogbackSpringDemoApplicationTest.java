package dev.gamov.colorfullogback.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ColorfulLogbackSpringDemoApplication.class)
@SpringJUnitConfig
class ColorfulLogbackSpringDemoApplicationTest {

    @Test
    void contextLoads() {
        // This test verifies that the Spring Boot application context loads successfully
        // If the context fails to load, this test will fail
        // This is a basic smoke test to ensure the application configuration is correct
    }

    @Test
    void main_shouldStartApplication() {
        // This test verifies that the main method can be called without throwing exceptions
        // We can't easily test the full application startup in a unit test,
        // but we can verify the method exists and is callable
        
        // Note: We're not actually calling main() here because it would start the full application
        // Instead, we're just verifying the method signature and that the class is properly structured
        
        // Verify the main method exists
        try {
            ColorfulLogbackSpringDemoApplication.class.getDeclaredMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Main method should exist", e);
        }
    }

    @Test
    void onApplicationReady_shouldCompleteWithoutException() {
        // Given
        ColorfulLogbackSpringDemoApplication application = new ColorfulLogbackSpringDemoApplication();
        
        // When & Then - the method should complete without throwing exceptions
        // This tests the event listener method that demonstrates log levels
        application.onApplicationReady();
        
        // If we reach this point, the method completed successfully
        // The actual logging verification would be done in integration tests
    }

    @Test
    void demonstrateLogLevels_shouldCompleteWithoutException() {
        // Given
        ColorfulLogbackSpringDemoApplication application = new ColorfulLogbackSpringDemoApplication();
        
        // When & Then - this private method is called by onApplicationReady()
        // We test it indirectly through onApplicationReady()
        // The method should complete without throwing exceptions
        application.onApplicationReady();
        
        // If we reach this point, the demonstrateLogLevels method (called within onApplicationReady) 
        // completed successfully
    }
}