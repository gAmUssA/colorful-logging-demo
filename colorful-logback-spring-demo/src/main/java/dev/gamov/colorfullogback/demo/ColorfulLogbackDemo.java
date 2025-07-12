package dev.gamov.colorfullogback.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo application to showcase the colorful logback library
 * This is a plain Java application without any Spring dependencies
 */
public class ColorfulLogbackDemo {
    
    private static final Logger logger = LoggerFactory.getLogger(ColorfulLogbackDemo.class);
    
    public static void main(String[] args) {
        logger.info("🎨 Starting Colorful Logback Library Demo");
        logger.info("📦 This is a plain Java application without Spring Boot");
        
        demonstrateAllLogLevels();
        demonstrateBusinessLogic();
        
        logger.info("✅ Colorful Logback Library Demo completed successfully!");
    }
    
    private static void demonstrateAllLogLevels() {
        logger.info("🔍 Demonstrating all log levels with colors:");
        
        logger.trace("🟣 TRACE: Most detailed logging level - appears in MAGENTA");
        logger.debug("🔵 DEBUG: Detailed information for debugging - appears in BLUE");
        logger.info("🟢 INFO: General information about application flow - appears in GREEN");
        logger.warn("🟡 WARN: Warning about potential issues - appears in YELLOW");
        logger.error("🔴 ERROR: Error events that might still allow application to continue - appears in RED");
    }
    
    private static void demonstrateBusinessLogic() {
        logger.info("🏪 Simulating business logic with colorful logging");
        
        try {
            logger.debug("🔍 Validating input parameters");
            Thread.sleep(100); // Simulate processing time
            
            logger.info("💳 Processing transaction");
            Thread.sleep(50);
            
            logger.debug("📧 Sending notification");
            Thread.sleep(30);
            
            logger.info("✅ Business operation completed successfully");
            
        } catch (InterruptedException e) {
            logger.error("❌ Business operation failed: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        
        // Demonstrate different package names
        Logger packageLogger = LoggerFactory.getLogger("com.example.service.BusinessService");
        packageLogger.info("📊 Package name coloring demonstration");
        
        Logger anotherLogger = LoggerFactory.getLogger("com.mycompany.util.Helper");
        anotherLogger.warn("⚠️ Different package name with warning level");
    }
}