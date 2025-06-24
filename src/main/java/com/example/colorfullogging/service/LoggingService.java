package com.example.colorfullogging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * Service to demonstrate colorful logging on application startup
 */
@Service
public class LoggingService implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("🎨 Colorful Logging Demo Application Started!");
        logger.info("📋 Available endpoints:");
        logger.info("   🔗 GET /api/logging/demo - Demonstrate all log levels");
        logger.info("   🔗 GET /api/logging/demo?level=error - Show only ERROR logs");
        logger.info("   🔗 GET /api/logging/demo?level=warn - Show only WARN logs");
        logger.info("   🔗 GET /api/logging/demo?level=info - Show only INFO logs");
        logger.info("   🔗 GET /api/logging/demo?level=debug - Show only DEBUG logs");
        logger.info("   🔗 GET /api/logging/demo?level=trace - Show only TRACE logs");
        logger.info("   🔗 GET /api/logging/health - Health check endpoint");
        logger.info("🌈 Enjoy the colorful logs in your console!");
        
        // Demonstrate different log levels on startup
        demonstrateStartupLogs();
    }

    private void demonstrateStartupLogs() {
        logger.trace("🟣 Application initialization trace");
        logger.debug("🔵 Loading configuration properties");
        logger.info("🟢 Service beans initialized successfully");
        logger.warn("🟡 Using default configuration for some properties");
        
        // Don't log error on startup unless there's an actual error
        logger.info("✨ Ready to serve colorful logging requests!");
    }
}
