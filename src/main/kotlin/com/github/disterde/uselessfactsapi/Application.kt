package com.github.disterde.uselessfactsapi

import com.github.disterde.uselessfactsapi.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

/**
 * The main entry point for the application.
 *
 * This function initializes and starts an embedded web server using the Ktor framework,
 * configured to run on port 8080 and host "0.0.0.0". The server setup includes various
 * modules like Koin for dependency injection, security, HTTP settings, monitoring, and
 * serialization.
 */
fun main() {
    embeddedServer(
        CIO,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

/**
 * Configures various application modules required for the smooth functioning of the application.
 *
 * This function sets up several aspects of the application including:
 * - Dependency injection through `configureKoin`.
 * - Security settings through `configureSecurity`.
 * - HTTP routing and exception handling through `configureHttp`.
 * - Monitoring and logging setup through `configureMonitoring`.
 * - JSON serialization settings through `configureSerialization`.
 */
fun Application.module() {
    configureKoin()
    configureSecurity()
    configureHttp()
    configureMonitoring()
    configureSerialization()
}
