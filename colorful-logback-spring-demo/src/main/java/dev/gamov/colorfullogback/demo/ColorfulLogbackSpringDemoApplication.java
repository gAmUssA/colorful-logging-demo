package dev.gamov.colorfullogback.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * Spring Boot application demonstrating the Colorful Logback Library
 */
@SpringBootApplication
public class ColorfulLogbackSpringDemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(ColorfulLogbackSpringDemoApplication.class);

    public static void main(String[] args) {
        logger.info("🚀 Starting Colorful Logback Spring Boot Demo Application");
        SpringApplication.run(ColorfulLogbackSpringDemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        logger.info("✅ Colorful Logback Spring Boot Demo Application is ready!");
        logger.info("🌐 Visit http://localhost:8080/demo to see the colorful logging in action");
        logger.info("📊 Visit http://localhost:8080/actuator/health for health check");
        
        // Demonstrate all log levels
        demonstrateLogLevels();
    }
    
    private void demonstrateLogLevels() {
        logger.info("🎨 Demonstrating colorful logging in Spring Boot:");
        logger.trace("🟣 TRACE: Most detailed logging level - appears in MAGENTA");
        logger.debug("🔵 DEBUG: Detailed information for debugging - appears in BLUE");
        logger.info("🟢 INFO: General information about application flow - appears in GREEN");
        logger.warn("🟡 WARN: Warning about potential issues - appears in YELLOW");
        logger.error("🔴 ERROR: Error events that might still allow application to continue - appears in RED");
    }
}