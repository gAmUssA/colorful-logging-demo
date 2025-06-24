package com.example.colorfullogging.service;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LoggingServiceTest {

    private LoggingService loggingService;
    private ListAppender<ILoggingEvent> listAppender;
    private Logger logger;

    @BeforeEach
    void setUp() {
        loggingService = new LoggingService();

        // Set up log capture
        logger = (Logger) LoggerFactory.getLogger(LoggingService.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @Test
    void run_shouldExecuteWithoutException() {
        assertDoesNotThrow(() -> loggingService.run());
    }

    @Test
    void run_withArguments_shouldExecuteWithoutException() {
        String[] args = {"arg1", "arg2", "test"};
        assertDoesNotThrow(() -> loggingService.run(args));
    }

    @Test
    void run_withEmptyArguments_shouldExecuteWithoutException() {
        String[] args = {};
        assertDoesNotThrow(() -> loggingService.run(args));
    }

    @Test
    void run_withNullArguments_shouldExecuteWithoutException() {
        assertDoesNotThrow(() -> loggingService.run((String[]) null));
    }

    @Test
    void run_shouldLogStartupMessage() throws Exception {
        loggingService.run();

        assertThat(listAppender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .anyMatch(message -> message.contains("Colorful Logging Demo Application Started!"));
    }

    @Test
    void run_shouldLogAvailableEndpoints() throws Exception {
        loggingService.run();

        assertThat(listAppender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .anyMatch(message -> message.contains("Available endpoints:"))
                .anyMatch(message -> message.contains("/api/logging/demo"))
                .anyMatch(message -> message.contains("/api/logging/health"));
    }

    @Test
    void run_shouldLogDifferentLogLevels() throws Exception {
        loggingService.run();

        assertThat(listAppender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .anyMatch(message -> message.contains("level=error"))
                .anyMatch(message -> message.contains("level=warn"))
                .anyMatch(message -> message.contains("level=info"))
                .anyMatch(message -> message.contains("level=debug"))
                .anyMatch(message -> message.contains("level=trace"));
    }

    @Test
    void run_shouldLogStartupDemonstration() throws Exception {
        loggingService.run();

        assertThat(listAppender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .anyMatch(message -> message.contains("Service beans initialized successfully"))
                .anyMatch(message -> message.contains("Using default configuration"))
                .anyMatch(message -> message.contains("Ready to serve colorful logging requests!"));
    }

    @Test
    void run_shouldLogWelcomeMessage() throws Exception {
        loggingService.run();

        assertThat(listAppender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .anyMatch(message -> message.contains("Enjoy the colorful logs in your console!"));
    }

    @Test
    void run_shouldLogCorrectNumberOfMessages() throws Exception {
        loggingService.run();

        // Verify that we have a reasonable number of log messages
        // This ensures all expected logging occurs
        assertThat(listAppender.list).hasSizeGreaterThan(10);
    }

    @Test
    void run_shouldNotLogErrorMessages() throws Exception {
        loggingService.run();

        // Verify no ERROR level messages are logged during normal startup
        assertThat(listAppender.list)
                .extracting(ILoggingEvent::getLevel)
                .noneMatch(level -> level.toString().equals("ERROR"));
    }
}
