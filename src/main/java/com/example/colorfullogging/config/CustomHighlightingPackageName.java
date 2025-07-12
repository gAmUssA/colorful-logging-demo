package com.example.colorfullogging.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.core.pattern.color.ANSIConstants;

/**
 * Custom converter for colorizing package names in console output
 */
public class CustomHighlightingPackageName extends BaseColorConverter {
    
    @Override
    protected String getColorForLevel(Level level) {
        switch (level.toInt()) {
            case Level.ERROR_INT:
                return ANSIConstants.RED_FG;
            case Level.WARN_INT:
                return ANSIConstants.YELLOW_FG;
            case Level.INFO_INT:
            case Level.DEBUG_INT:
            case Level.TRACE_INT:
            default:
                return ANSIConstants.CYAN_FG;
        }
    }
}
