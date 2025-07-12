# Colorful Logback - Multimodule Project

A multimodule Maven project that provides a lightweight Java library for adding beautiful colors to your logback console output, along with a Spring Boot demo application.

## 🏗️ Project Structure

This project is organized as a multimodule Maven project:

```
colorful-logback-parent/
├── colorful-logback/              # The core library
├── colorful-logback-spring-demo/  # Spring Boot demo application
├── pom.xml                        # Parent POM
├── jitpack.yml                    # JitPack configuration
└── README.md                      # This file
```

## 📦 Modules

### 1. Colorful Logback Library (`colorful-logback`)

The core library that provides colorful logging functionality for any Java project using logback.

**Features:**
- 🎨 **Colorful Console Output**: Different colors for different log levels
- 🚀 **Framework Independent**: Works with any Java project using logback
- 🔧 **Easy Integration**: Simple configuration with logback.xml
- 📦 **Lightweight**: Minimal dependencies (only logback)
- ⚡ **High Performance**: Optimized for efficiency

**Color Scheme:**
- **ERROR**: Red
- **WARN**: Yellow  
- **INFO**: Green
- **DEBUG**: Blue
- **TRACE**: Magenta
- **Package Names**: Cyan (for INFO, DEBUG, TRACE), Red/Yellow (for ERROR/WARN)

### 2. Spring Boot Demo (`colorful-logback-spring-demo`)

A complete Spring Boot application demonstrating how to use the colorful logback library in a real-world scenario.

**Features:**
- 🌐 REST API endpoints for testing colorful logging
- 🔧 Service layer with business logic demonstrations
- 📊 Actuator endpoints for monitoring
- 🎯 Profile-based logging configuration
- 📝 Comprehensive logging examples

## 🚀 Quick Start

### Using the Library via JitPack

#### Maven

Add the JitPack repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the dependency:

```xml
<dependency>
    <groupId>com.github.vikgamov.colorful-logging-demo</groupId>
    <artifactId>colorful-logback</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Gradle

Add the JitPack repository to your `build.gradle`:

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency:

```gradle
dependencies {
    implementation 'com.github.vikgamov.colorful-logging-demo:colorful-logback:1.0.0'
}
```

### Configuration

Create or update your `logback.xml` file in `src/main/resources/`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- Define custom conversion rules for colorful logging -->
    <conversionRule conversionWord="colorLevel" 
                   converterClass="dev.gamov.colorfullogback.CustomHighlightingLogLevel" />
    <conversionRule conversionWord="colorPackage" 
                   converterClass="dev.gamov.colorfullogback.CustomHighlightingPackageName" />
    <conversionRule conversionWord="colorMessage" 
                   converterClass="dev.gamov.colorfullogback.CustomHighlightingMessage" />

    <!-- Console appender with colorful output -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%colorLevel(%d{HH:mm:ss.SSS} %-5level) %colorPackage(%logger{20}): %colorMessage(%msg%n)</pattern>
        </encoder>
    </appender>

    <!-- Root logger with colorful console output -->
    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

### Java Code Example

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyApplication {
    private static final Logger logger = LoggerFactory.getLogger(MyApplication.class);
    
    public static void main(String[] args) {
        logger.trace("🟣 This is a TRACE message - appears in MAGENTA");
        logger.debug("🔵 This is a DEBUG message - appears in BLUE");
        logger.info("🟢 This is an INFO message - appears in GREEN");
        logger.warn("🟡 This is a WARNING message - appears in YELLOW");
        logger.error("🔴 This is an ERROR message - appears in RED");
    }
}
```

## 🏃‍♂️ Running the Demo

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Build the Project

```bash
git clone https://github.com/vikgamov/colorful-logging-demo.git
cd colorful-logging-demo
mvn clean install
```

### Run the Spring Boot Demo

```bash
cd colorful-logback-spring-demo
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Demo Endpoints

- **GET** `/demo` - Demonstrate colorful logging in business operations
- **POST** `/demo/simulate-error` - Simulate error scenarios with colorful logging
- **GET** `/demo/levels` - Demonstrate all log levels with colors
- **GET** `/actuator/health` - Health check endpoint

### Example Usage

```bash
# Test basic logging demonstration
curl http://localhost:8080/demo

# Test all log levels
curl http://localhost:8080/demo/levels

# Test error simulation
curl -X POST http://localhost:8080/demo/simulate-error

# Check application health
curl http://localhost:8080/actuator/health
```

## 🔧 Advanced Configuration

### Spring Boot with Profiles

For Spring Boot applications, use `logback-spring.xml` for profile-specific configurations:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- Define custom conversion rules -->
    <conversionRule conversionWord="colorLevel" 
                   converterClass="dev.gamov.colorfullogback.CustomHighlightingLogLevel" />
    <conversionRule conversionWord="colorPackage" 
                   converterClass="dev.gamov.colorfullogback.CustomHighlightingPackageName" />
    <conversionRule conversionWord="colorMessage" 
                   converterClass="dev.gamov.colorfullogback.CustomHighlightingMessage" />

    <!-- Development profile - colorful console output -->
    <springProfile name="!prod">
        <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <pattern>%colorLevel(%d{HH:mm:ss.SSS} %-5level) [%thread] %colorPackage(%logger{36}): %colorMessage(%msg%n)</pattern>
            </encoder>
        </appender>
        
        <root level="INFO">
            <appender-ref ref="STDOUT"/>
        </root>
    </springProfile>

    <!-- Production profile - file logging only -->
    <springProfile name="prod">
        <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <file>logs/application.log</file>
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <fileNamePattern>logs/application.%d{yyyy-MM-dd}.log</fileNamePattern>
                <maxHistory>30</maxHistory>
            </rollingPolicy>
            <encoder>
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%thread] %logger{36}: %msg%n</pattern>
            </encoder>
        </appender>
        
        <root level="WARN">
            <appender-ref ref="FILE"/>
        </root>
    </springProfile>
</configuration>
```

### Custom Color Converters

You can extend the base converter classes to create your own color schemes:

```java
package dev.gamov.colorfullogback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.core.pattern.color.ANSIConstants;

public class MyCustomColorConverter extends BaseColorConverter {
    @Override
    protected String getColorForLevel(Level level) {
        switch (level.toInt()) {
            case Level.ERROR_INT:
                return ANSIConstants.BOLD + ANSIConstants.RED_FG;
            case Level.WARN_INT:
                return ANSIConstants.BOLD + ANSIConstants.YELLOW_FG;
            // ... customize other levels
            default:
                return ANSIConstants.DEFAULT_FG;
        }
    }
}
```

## 📚 Available Conversion Words

- `%colorLevel(...)`: Colors the content based on log level
- `%colorPackage(...)`: Colors package names with level-specific colors
- `%colorMessage(...)`: Colors log messages based on log level

## 🔄 Development

### Building from Source

```bash
# Clone the repository
git clone https://github.com/vikgamov/colorful-logging-demo.git
cd colorful-logging-demo

# Build all modules
mvn clean compile

# Run tests
mvn test

# Install to local repository
mvn install

# Run the Spring Boot demo
cd colorful-logback-spring-demo
mvn spring-boot:run
```

### Module Dependencies

The Spring Boot demo depends on the library module:

```xml
<dependency>
    <groupId>dev.gamov</groupId>
    <artifactId>colorful-logback</artifactId>
    <version>${project.version}</version>
</dependency>
```

## 📋 Requirements

- Java 17 or higher
- Logback Classic (managed by Spring Boot BOM)
- SLF4J API (included with Logback Classic)

## 🔗 Compatibility

This library is compatible with:
- ✅ Plain Java applications
- ✅ Spring Boot applications
- ✅ Spring Framework applications
- ✅ Any Java framework using logback
- ✅ Maven and Gradle projects

## 🚀 Performance

The library is designed for high performance:
- Minimal overhead on logging operations
- Efficient color code generation
- No reflection or heavy computations during logging
- Provided scope for logback dependency to avoid version conflicts

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📞 Support

If you encounter any issues or have questions, please open an issue on the project repository.

## 🏷️ Versioning

This project uses [JitPack](https://jitpack.io) for publishing. To use a specific version:

- **Latest Release**: Use the latest tag (e.g., `1.0.0`)
- **Latest Commit**: Use `main-SNAPSHOT`
- **Specific Commit**: Use the commit hash

Example with specific version:
```xml
<dependency>
    <groupId>com.github.vikgamov.colorful-logging-demo</groupId>
    <artifactId>colorful-logback</artifactId>
    <version>1.0.0</version>
</dependency>
```