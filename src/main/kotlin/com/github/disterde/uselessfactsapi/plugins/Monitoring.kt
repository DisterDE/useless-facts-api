package com.github.disterde.uselessfactsapi.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*
import org.slf4j.event.Level

/**
 * Configures the monitoring for the application.
 *
 * This function installs the CallLogging feature to log incoming HTTP requests.
 * It sets the logging level to INFO and applies a filter to log requests
 * whose paths start with the root ("/").
 *
 * The CallLogging feature helps in tracing and debugging by providing visibility
 * into the request processing pipeline.
 */
fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
}
