# 🎨 Colorful Logging Demo - Makefile
# Using emoji and colors for better readability

# Colors
RED=\033[0;31m
GREEN=\033[0;32m
YELLOW=\033[1;33m
BLUE=\033[0;34m
PURPLE=\033[0;35m
CYAN=\033[0;36m
NC=\033[0m # No Color

# Project variables
PROJECT_NAME=colorful-logging-demo
JAR_FILE=target/$(PROJECT_NAME)-1.0.0.jar
MAVEN_OPTS=-Dmaven.test.skip=true

.PHONY: help clean compile test package run dev logs health demo stop

help: ## 📋 Show this help message
	@echo "$(CYAN)🎨 Colorful Logging Demo - Available Commands$(NC)"
	@echo ""
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "$(YELLOW)%-15s$(NC) %s\n", $$1, $$2}'

clean: ## 🧹 Clean build artifacts
	@echo "$(YELLOW)🧹 Cleaning build artifacts...$(NC)"
	@mvn clean
	@echo "$(GREEN)✅ Clean completed!$(NC)"

compile: ## 🔨 Compile the project
	@echo "$(BLUE)🔨 Compiling project...$(NC)"
	@mvn compile
	@echo "$(GREEN)✅ Compilation completed!$(NC)"

test: ## 🧪 Run tests
	@echo "$(PURPLE)🧪 Running tests...$(NC)"
	@mvn test
	@echo "$(GREEN)✅ Tests completed!$(NC)"

package: clean ## 📦 Package the application
	@echo "$(BLUE)📦 Packaging application...$(NC)"
	@mvn package $(MAVEN_OPTS)
	@echo "$(GREEN)✅ Packaging completed!$(NC)"
	@echo "$(CYAN)📄 JAR file: $(JAR_FILE)$(NC)"

run: package ## 🚀 Run the application
	@echo "$(GREEN)🚀 Starting Colorful Logging Demo...$(NC)"
	@echo "$(CYAN)🌐 Application will be available at: http://localhost:8080$(NC)"
	@java -jar $(JAR_FILE)

dev: ## 🛠️ Run in development mode with Maven
	@echo "$(GREEN)🛠️ Starting in development mode...$(NC)"
	@echo "$(CYAN)🌐 Application will be available at: http://localhost:8080$(NC)"
	@mvn spring-boot:run

logs: ## 📋 Show application logs
	@echo "$(CYAN)📋 Showing recent logs...$(NC)"
	@if [ -f "logs/colorful-logging-demo.log" ]; then \
		tail -f logs/colorful-logging-demo.log; \
	else \
		echo "$(RED)❌ Log file not found. Make sure the application is running.$(NC)"; \
	fi

health: ## 🏥 Check application health
	@echo "$(CYAN)🏥 Checking application health...$(NC)"
	@curl -s http://localhost:8080/api/logging/health | jq '.' || echo "$(RED)❌ Application not responding$(NC)"

demo: ## 🎨 Run logging demonstration
	@echo "$(PURPLE)🎨 Running colorful logging demonstration...$(NC)"
	@echo "$(YELLOW)📊 All levels:$(NC)"
	@curl -s "http://localhost:8080/api/logging/demo" | jq '.'
	@echo ""
	@echo "$(RED)🔴 Error level:$(NC)"
	@curl -s "http://localhost:8080/api/logging/demo?level=error" | jq '.'
	@echo ""
	@echo "$(YELLOW)🟡 Warning level:$(NC)"
	@curl -s "http://localhost:8080/api/logging/demo?level=warn" | jq '.'
	@echo ""
	@echo "$(GREEN)🟢 Info level:$(NC)"
	@curl -s "http://localhost:8080/api/logging/demo?level=info" | jq '.'

stop: ## 🛑 Stop the application (if running in background)
	@echo "$(RED)🛑 Stopping application...$(NC)"
	@pkill -f "colorful-logging-demo" || echo "$(YELLOW)⚠️ No running application found$(NC)"
	@echo "$(GREEN)✅ Application stopped!$(NC)"

install-deps: ## 📥 Install required dependencies (jq, curl)
	@echo "$(BLUE)📥 Checking dependencies...$(NC)"
	@command -v jq >/dev/null 2>&1 || { echo "$(YELLOW)⚠️ Installing jq...$(NC)"; brew install jq; }
	@command -v curl >/dev/null 2>&1 || { echo "$(YELLOW)⚠️ Installing curl...$(NC)"; brew install curl; }
	@echo "$(GREEN)✅ Dependencies ready!$(NC)"

quick-start: install-deps dev ## ⚡ Quick start (install deps + run in dev mode)

all: clean compile test package ## 🏗️ Full build pipeline

.DEFAULT_GOAL := help
