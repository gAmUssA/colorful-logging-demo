package com.example.colorfullogging.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoggingDemoController.class)
class LoggingDemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void demonstrateLogging_withDefaultLevel_shouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/logging/demo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Check your console for colorful logs!"))
                .andExpect(jsonPath("$.level").value("all"));
    }

    @Test
    void demonstrateLogging_withErrorLevel_shouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/logging/demo").param("level", "error"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Check your console for colorful logs!"))
                .andExpect(jsonPath("$.level").value("error"));
    }

    @Test
    void demonstrateLogging_withWarnLevel_shouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/logging/demo").param("level", "warn"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Check your console for colorful logs!"))
                .andExpect(jsonPath("$.level").value("warn"));
    }

    @Test
    void demonstrateLogging_withInfoLevel_shouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/logging/demo").param("level", "info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Check your console for colorful logs!"))
                .andExpect(jsonPath("$.level").value("info"));
    }

    @Test
    void demonstrateLogging_withDebugLevel_shouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/logging/demo").param("level", "debug"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Check your console for colorful logs!"))
                .andExpect(jsonPath("$.level").value("debug"));
    }

    @Test
    void demonstrateLogging_withTraceLevel_shouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/logging/demo").param("level", "trace"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Check your console for colorful logs!"))
                .andExpect(jsonPath("$.level").value("trace"));
    }

    @Test
    void demonstrateLogging_withCaseInsensitiveLevel_shouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/logging/demo").param("level", "ERROR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Check your console for colorful logs!"))
                .andExpect(jsonPath("$.level").value("ERROR"));
    }

    @Test
    void demonstrateLogging_withInvalidLevel_shouldDefaultToAll() throws Exception {
        mockMvc.perform(get("/api/logging/demo").param("level", "invalid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Check your console for colorful logs!"))
                .andExpect(jsonPath("$.level").value("invalid"));
    }

    @Test
    void healthCheck_shouldReturnHealthyStatus() throws Exception {
        mockMvc.perform(get("/api/logging/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("healthy"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void healthCheck_shouldReturnValidTimestamp() throws Exception {
        mockMvc.perform(get("/api/logging/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp").isString())
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }
}