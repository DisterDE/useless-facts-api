package com.github.disterde.uselessfactsapi.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

/**
 * Configures JSON serialization for the application using the ContentNegotiation feature.
 *
 * This function installs the ContentNegotiation feature and sets up JSON
 * serialization with specific configurations:
 * - Ignores unknown keys in the JSON payload.
 * - Pretty prints the JSON output.
 * - Uses SnakeCase naming strategy for JSON properties.
 *
 * This configuration aims to handle common JSON serialization needs
 * while ensuring the application can smoothly handle diverse JSON structures.
 */
@OptIn(ExperimentalSerializationApi::class)
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            json = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                namingStrategy = JsonNamingStrategy.SnakeCase
            }
        )
    }
}
