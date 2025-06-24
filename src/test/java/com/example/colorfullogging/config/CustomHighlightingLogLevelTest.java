package com.example.colorfullogging.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CustomHighlightingLogLevelTest {

    private CustomHighlightingLogLevel customHighlightingLogLevel;

    @Mock
    private ILoggingEvent mockLoggingEvent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customHighlightingLogLevel = new CustomHighlightingLogLevel();
    }

    @Test
    void getForegroundColorCode_withErrorLevel_shouldReturnRedColor() {
        when(mockLoggingEvent.getLevel()).thenReturn(Level.ERROR);
        
        String colorCode = customHighlightingLogLevel.getForegroundColorCode(mockLoggingEvent);
        
        assertThat(colorCode).isEqualTo(ANSIConstants.RED_FG);
    }

    @Test
    void getForegroundColorCode_withWarnLevel_shouldReturnYellowColor() {
        when(mockLoggingEvent.getLevel()).thenReturn(Level.WARN);
        
        String colorCode = customHighlightingLogLevel.getForegroundColorCode(mockLoggingEvent);
        
        assertThat(colorCode).isEqualTo(ANSIConstants.YELLOW_FG);
    }

    @Test
    void getForegroundColorCode_withInfoLevel_shouldReturnGreenColor() {
        when(mockLoggingEvent.getLevel()).thenReturn(Level.INFO);
        
        String colorCode = customHighlightingLogLevel.getForegroundColorCode(mockLoggingEvent);
        
        assertThat(colorCode).isEqualTo(ANSIConstants.GREEN_FG);
    }

    @Test
    void getForegroundColorCode_withDebugLevel_shouldReturnBlueColor() {
        when(mockLoggingEvent.getLevel()).thenReturn(Level.DEBUG);
        
        String colorCode = customHighlightingLogLevel.getForegroundColorCode(mockLoggingEvent);
        
        assertThat(colorCode).isEqualTo(ANSIConstants.BLUE_FG);
    }

    @Test
    void getForegroundColorCode_withTraceLevel_shouldReturnMagentaColor() {
        when(mockLoggingEvent.getLevel()).thenReturn(Level.TRACE);
        
        String colorCode = customHighlightingLogLevel.getForegroundColorCode(mockLoggingEvent);
        
        assertThat(colorCode).isEqualTo(ANSIConstants.MAGENTA_FG);
    }

    @Test
    void getForegroundColorCode_withOffLevel_shouldReturnDefaultColor() {
        when(mockLoggingEvent.getLevel()).thenReturn(Level.OFF);
        
        String colorCode = customHighlightingLogLevel.getForegroundColorCode(mockLoggingEvent);
        
        assertThat(colorCode).isEqualTo(ANSIConstants.DEFAULT_FG);
    }

    @Test
    void getForegroundColorCode_withAllLevel_shouldReturnDefaultColor() {
        when(mockLoggingEvent.getLevel()).thenReturn(Level.ALL);
        
        String colorCode = customHighlightingLogLevel.getForegroundColorCode(mockLoggingEvent);
        
        assertThat(colorCode).isEqualTo(ANSIConstants.DEFAULT_FG);
    }

    @Test
    void getForegroundColorCode_withNullLevel_shouldReturnDefaultColor() {
        when(mockLoggingEvent.getLevel()).thenReturn(null);
        
        String colorCode = customHighlightingLogLevel.getForegroundColorCode(mockLoggingEvent);
        
        assertThat(colorCode).isEqualTo(ANSIConstants.DEFAULT_FG);
    }

    @Test
    void getForegroundColorCode_shouldHandleAllLogLevels() {
        // Test all standard log levels to ensure complete coverage
        Level[] levels = {Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE};
        String[] expectedColors = {
            ANSIConstants.RED_FG,
            ANSIConstants.YELLOW_FG,
            ANSIConstants.GREEN_FG,
            ANSIConstants.BLUE_FG,
            ANSIConstants.MAGENTA_FG
        };

        for (int i = 0; i < levels.length; i++) {
            when(mockLoggingEvent.getLevel()).thenReturn(levels[i]);
            String colorCode = customHighlightingLogLevel.getForegroundColorCode(mockLoggingEvent);
            assertThat(colorCode).isEqualTo(expectedColors[i]);
        }
    }
}