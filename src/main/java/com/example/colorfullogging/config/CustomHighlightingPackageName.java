package com.example.colorfullogging.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

/**
 * Custom converter for colorizing package names in console output
 */
public class CustomHighlightingPackageName extends ForegroundCompositeConverterBase<ILoggingEvent> {
    
    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        Level level = event.getLevel();
        switch (level.toInt()) {
            case Level.ERROR_INT:
                return ANSIConstants.RED_FG;
            case Level.WARN_INT:
                return ANSIConstants.YELLOW_FG;
            case Level.INFO_INT:
                return ANSIConstants.CYAN_FG;
            case Level.DEBUG_INT:
                return ANSIConstants.CYAN_FG;
            case Level.TRACE_INT:
                return ANSIConstants.CYAN_FG;
            default:
                return ANSIConstants.CYAN_FG;
        }
    }
}
