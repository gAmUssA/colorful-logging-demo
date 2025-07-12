package dev.gamov.colorfullogback.demo.controller;

import dev.gamov.colorfullogback.demo.service.LoggingDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller demonstrating colorful logging in Spring Boot
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    
    @Autowired
    private LoggingDemoService loggingDemoService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> demonstrateLogging() {
        logger.info("🌐 HTTP GET /demo - Demonstrating colorful logging");
        
        try {
            Map<String, Object> result = loggingDemoService.performBusinessOperation();
            logger.info("✅ Successfully processed demo request");
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            logger.error("❌ Error processing demo request: {}", e.getMessage());
            return ResponseEntity.internalServerError()
                .body(Map.of("error", "Internal server error", "message", e.getMessage()));
        }
    }

    @PostMapping("/simulate-error")
    public ResponseEntity<Map<String, Object>> simulateError() {
        logger.warn("⚠️ HTTP POST /demo/simulate-error - Simulating error scenario");
        
        try {
            loggingDemoService.simulateErrorScenario();
            return ResponseEntity.ok(Map.of("message", "This shouldn't happen"));
            
        } catch (Exception e) {
            logger.error("🔥 Simulated error occurred: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Simulated error", "message", e.getMessage()));
        }
    }

    @GetMapping("/levels")
    public ResponseEntity<Map<String, Object>> demonstrateAllLevels() {
        logger.info("🎨 HTTP GET /demo/levels - Demonstrating all log levels");
        
        logger.trace("🟣 TRACE: Most detailed logging level");
        logger.debug("🔵 DEBUG: Detailed information for debugging");
        logger.info("🟢 INFO: General information about application flow");
        logger.warn("🟡 WARN: Warning about potential issues");
        logger.error("🔴 ERROR: Error events (this is just a demo, not a real error)");
        
        return ResponseEntity.ok(Map.of(
            "message", "All log levels demonstrated",
            "levels", new String[]{"TRACE", "DEBUG", "INFO", "WARN", "ERROR"},
            "colors", new String[]{"MAGENTA", "BLUE", "GREEN", "YELLOW", "RED"}
        ));
    }
}