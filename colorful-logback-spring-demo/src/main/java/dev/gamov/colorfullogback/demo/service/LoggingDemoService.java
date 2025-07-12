package dev.gamov.colorfullogback.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

/**
 * Service demonstrating colorful logging in various business scenarios
 */
@Service
public class LoggingDemoService {

    private static final Logger logger = LoggerFactory.getLogger(LoggingDemoService.class);
    private final Random random = new Random();

    public Map<String, Object> performBusinessOperation() {
        logger.info("🏪 Starting business operation");
        
        try {
            // Simulate validation
            logger.debug("🔍 Validating input parameters");
            Thread.sleep(50);
            
            // Simulate processing
            logger.info("⚙️ Processing business logic");
            Thread.sleep(100);
            
            // Simulate data access
            logger.debug("💾 Accessing database");
            Thread.sleep(30);
            
            // Simulate external service call
            logger.info("🌐 Calling external service");
            Thread.sleep(80);
            
            // Random warning scenario
            if (random.nextInt(10) < 3) {
                logger.warn("⚠️ Performance degradation detected - operation took longer than expected");
            }
            
            logger.info("✅ Business operation completed successfully");
            
            return Map.of(
                "status", "success",
                "timestamp", LocalDateTime.now().toString(),
                "operation", "business-demo",
                "duration", "~260ms",
                "message", "Colorful logging demonstration completed"
            );
            
        } catch (InterruptedException e) {
            logger.error("❌ Business operation interrupted: {}", e.getMessage());
            Thread.currentThread().interrupt();
            throw new RuntimeException("Operation interrupted", e);
        }
    }

    public void simulateErrorScenario() {
        logger.info("🎭 Simulating error scenario for demonstration");
        
        try {
            logger.debug("🔍 Preparing error simulation");
            Thread.sleep(20);
            
            logger.warn("⚠️ Entering unstable code path");
            
            // Simulate different types of errors
            int errorType = random.nextInt(3);
            switch (errorType) {
                case 0:
                    logger.error("💥 Simulated NullPointerException");
                    throw new NullPointerException("Simulated null pointer for demo");
                case 1:
                    logger.error("🚫 Simulated IllegalArgumentException");
                    throw new IllegalArgumentException("Simulated invalid argument for demo");
                default:
                    logger.error("🔥 Simulated RuntimeException");
                    throw new RuntimeException("Simulated runtime exception for demo");
            }
            
        } catch (InterruptedException e) {
            logger.error("❌ Error simulation interrupted: {}", e.getMessage());
            Thread.currentThread().interrupt();
            throw new RuntimeException("Simulation interrupted", e);
        }
    }

    public void demonstratePackageLogging() {
        logger.info("📦 Demonstrating package-specific logging colors");
        
        // Create loggers for different packages to show package name coloring
        Logger serviceLogger = LoggerFactory.getLogger("com.example.service.BusinessService");
        Logger repositoryLogger = LoggerFactory.getLogger("com.example.repository.DataRepository");
        Logger utilLogger = LoggerFactory.getLogger("com.example.util.Helper");
        
        serviceLogger.info("🔧 Service layer logging with package coloring");
        repositoryLogger.debug("💾 Repository layer logging with package coloring");
        utilLogger.warn("⚠️ Utility layer logging with package coloring");
        
        logger.info("🎨 Package name coloring demonstration completed");
    }
}