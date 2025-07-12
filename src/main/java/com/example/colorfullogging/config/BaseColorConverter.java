package com.example.colorfullogging.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

/**
 * Base abstract class for custom colorful logging converters.
 * Provides common color mapping logic and null safety checks.
 */
public abstract class BaseColorConverter extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        Level level = event.getLevel();
        if (level == null) {
            return ANSIConstants.DEFAULT_FG;
        }
        return getColorForLevel(level);
    }

    /**
     * Get the ANSI color code for the given log level.
     * Subclasses can override this method to provide custom color mappings.
     * 
     * @param level the log level
     * @return the ANSI color code
     */
    protected String getColorForLevel(Level level) {
        switch (level.toInt()) {
            case Level.ERROR_INT:
                return ANSIConstants.RED_FG;
            case Level.WARN_INT:
                return ANSIConstants.YELLOW_FG;
            case Level.INFO_INT:
                return ANSIConstants.GREEN_FG;
            case Level.DEBUG_INT:
                return ANSIConstants.BLUE_FG;
            case Level.TRACE_INT:
                return ANSIConstants.MAGENTA_FG;
            default:
                return ANSIConstants.DEFAULT_FG;
        }
    }
}