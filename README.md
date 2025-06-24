# 🎨 Colorful Logging Demo - Spring Boot

This Spring Boot application demonstrates how to implement colorful logging using Logback with custom converters, based on the Medium article by AJawhar.

## 🌈 Features

- **Colorful Console Logging**: Different log levels appear in different colors
  - 🔴 **ERROR**: Red
  - 🟡 **WARN**: Yellow  
  - 🟢 **INFO**: Green
  - 🔵 **DEBUG**: Blue
  - 🟣 **TRACE**: Magenta
- **Custom Converters**: Separate color customization for log levels, package names, and messages
- **File Logging**: Plain text logging to files without colors
- **REST API**: Endpoints to demonstrate different logging levels
- **Emoji Support**: Enhanced readability with emojis in log messages

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Running the Application

```bash
# Navigate to project directory
cd colorful-logging-demo

# Run with Maven
mvn spring-boot:run

# Or build and run JAR
mvn clean package
java -jar target/colorful-logging-demo-1.0.0.jar
```

## 🔗 API Endpoints

Once the application is running, visit these endpoints:

- **Demo All Levels**: `GET http://localhost:8080/api/logging/demo`
- **Error Only**: `GET http://localhost:8080/api/logging/demo?level=error`
- **Warning Only**: `GET http://localhost:8080/api/logging/demo?level=warn`
- **Info Only**: `GET http://localhost:8080/api/logging/demo?level=info`
- **Debug Only**: `GET http://localhost:8080/api/logging/demo?level=debug`
- **Trace Only**: `GET http://localhost:8080/api/logging/demo?level=trace`
- **Health Check**: `GET http://localhost:8080/api/logging/health`

## 🏗️ Project Structure

```
src/main/java/com/example/colorfullogging/
├── ColorfulLoggingDemoApplication.java    # Main application class
├── config/
│   ├── CustomHighlightingLogLevel.java    # Log level colorizer
│   ├── CustomHighlightingMessage.java     # Message colorizer
│   └── CustomHighlightingPackageName.java # Package name colorizer
├── controller/
│   └── LoggingDemoController.java         # REST endpoints
└── service/
    └── LoggingService.java                # Startup logging demo

src/main/resources/
├── logback-spring.xml                     # Logback configuration
└── application.yml                        # Spring Boot configuration
```

## ⚙️ Configuration

### Logback Configuration (`logback-spring.xml`)

The configuration defines:
- Custom conversion rules for colorful output
- Console appender with colors
- File appender without colors (plain text)
- Different loggers for application and framework

### Custom Converters

Three custom converter classes extend `ForegroundCompositeConverterBase`:

1. **CustomHighlightingLogLevel**: Colors the log level (ERROR, WARN, etc.)
2. **CustomHighlightingMessage**: Colors the actual log message
3. **CustomHighlightingPackageName**: Colors the package/class name

## 🎯 Key Implementation Details

### Color Mapping
- Uses Logback's `ANSIConstants` for color codes
- Each log level maps to a specific color
- Package names use cyan for better readability

### Pattern Format
```
%customHighlightingLogLevel(%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level) 
%customHighlightingPackageName([%thread] %logger{36}.%M\(%line\)): 
%customHighlightingMessage(%msg%n)
```

## 📝 Log Output Examples

When you run the application, you'll see colorful output like:

```
2024-06-24 06:24:00.123 INFO  [main] c.e.c.service.LoggingService: 🎨 Colorful Logging Demo Application Started!
2024-06-24 06:24:00.124 ERROR [http-nio-8080-exec-1] c.e.c.controller.LoggingDemoController: 🔴 This is an ERROR message
2024-06-24 06:24:00.125 WARN  [http-nio-8080-exec-1] c.e.c.controller.LoggingDemoController: 🟡 This is a WARNING message
2024-06-24 06:24:00.126 INFO  [http-nio-8080-exec-1] c.e.c.controller.LoggingDemoController: 🟢 This is an INFO message
```

## 🛠️ Customization

To customize colors, modify the converter classes:

```java
@Override
protected String getForegroundColorCode(ILoggingEvent event) {
    Level level = event.getLevel();
    switch (level.toInt()) {
        case Level.ERROR_INT:
            return ANSIConstants.RED_FG;        // Change to your preferred color
        case Level.WARN_INT:
            return ANSIConstants.YELLOW_FG;     // Change to your preferred color
        // ... other levels
    }
}
```

Available colors in `ANSIConstants`:
- `RED_FG`, `GREEN_FG`, `YELLOW_FG`, `BLUE_FG`, `MAGENTA_FG`, `CYAN_FG`
- `BOLD`, `DEFAULT_FG`

## 📚 References

- [Original Medium Article](https://medium.com/@alaajawhar123/colorful-logging-in-spring-da2722bc08d1)
- [Logback Documentation](http://logback.qos.ch/manual/)
- [Spring Boot Logging](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)

## 🤝 Contributing

Feel free to submit issues and enhancement requests!

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
