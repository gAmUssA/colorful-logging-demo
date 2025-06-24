package com.example.colorfullogging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller to demonstrate colorful logging at different levels
 */
@RestController
@RequestMapping("/api/logging")
public class LoggingDemoController {

    private static final Logger logger = LoggerFactory.getLogger(LoggingDemoController.class);

    @GetMapping("/demo")
    public Map<String, String> demonstrateLogging(@RequestParam(defaultValue = "all") String level) {
        logger.info("🎨 Starting colorful logging demonstration for level: {}", level);

        switch (level.toLowerCase()) {
            case "error":
                demonstrateErrorLogging();
                break;
            case "warn":
                demonstrateWarnLogging();
                break;
            case "info":
                demonstrateInfoLogging();
                break;
            case "debug":
                demonstrateDebugLogging();
                break;
            case "trace":
                demonstrateTraceLogging();
                break;
            default:
                demonstrateAllLevels();
        }

        logger.info("✅ Colorful logging demonstration completed!");
        return Map.of(
            "status", "success",
            "message", "Check your console for colorful logs!",
            "level", level
        );
    }

    private void demonstrateErrorLogging() {
        logger.error("🔴 This is an ERROR message - should appear in RED");
        logger.error("💥 Simulating database connection failure");
        logger.error("⚠️ Critical system error occurred at {}", System.currentTimeMillis());
    }

    private void demonstrateWarnLogging() {
        logger.warn("🟡 This is a WARNING message - should appear in YELLOW");
        logger.warn("⚡ Memory usage is above 80%");
        logger.warn("🔔 Deprecated API usage detected");
    }

    private void demonstrateInfoLogging() {
        logger.info("🟢 This is an INFO message - should appear in GREEN");
        logger.info("🚀 Application started successfully");
        logger.info("📊 Processing {} records", 1000);
    }

    private void demonstrateDebugLogging() {
        logger.debug("🔵 This is a DEBUG message - should appear in BLUE");
        logger.debug("🔍 Variable value: {}", "sample_value");
        logger.debug("🛠️ Method execution time: {} ms", 150);
    }

    private void demonstrateTraceLogging() {
        logger.trace("🟣 This is a TRACE message - should appear in MAGENTA");
        logger.trace("📝 Entering method with parameters: {}", "param1, param2");
        logger.trace("🔄 Loop iteration: {}", 5);
    }

    private void demonstrateAllLevels() {
        logger.trace("🟣 TRACE: Most detailed logging level");
        logger.debug("🔵 DEBUG: Detailed information for debugging");
        logger.info("🟢 INFO: General information about application flow");
        logger.warn("🟡 WARN: Warning about potential issues");
        logger.error("🔴 ERROR: Error events that might still allow application to continue");
        
        // Simulate some business logic with logging
        simulateBusinessLogic();
    }

    private void simulateBusinessLogic() {
        logger.info("🏪 Starting business transaction");
        
        try {
            logger.debug("🔍 Validating input parameters");
            Thread.sleep(100); // Simulate processing time
            
            logger.info("💳 Processing payment");
            Thread.sleep(50);
            
            logger.debug("📧 Sending confirmation email");
            Thread.sleep(30);
            
            logger.info("✅ Transaction completed successfully");
            
        } catch (InterruptedException e) {
            logger.error("❌ Transaction failed: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        logger.info("🏥 Health check endpoint called");
        return Map.of("status", "healthy", "timestamp", String.valueOf(System.currentTimeMillis()));
    }
}
