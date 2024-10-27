package com.github.disterde.uselessfactsapi.plugins

import com.github.disterde.uselessfactsapi.exception.ApiException
import com.github.disterde.uselessfactsapi.exception.NoElementInCacheException
import com.github.disterde.uselessfactsapi.exception.ValidationException
import com.github.disterde.uselessfactsapi.route.adminRoute
import com.github.disterde.uselessfactsapi.route.factsRoute
import com.github.disterde.uselessfactsapi.service.FactStatisticsFacade
import com.github.disterde.uselessfactsapi.service.StatisticsService
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

/**
 * Configures the HTTP settings for the application.
 *
 * This function sets up routing for both public and admin routes,
 * handles exceptions, and provides a Swagger UI for API documentation.
 *
 * - Injects dependencies for `FactStatisticsFacade` and `StatisticsService`.
 * - Configures routes for facts and admin functionality.
 * - Installs status pages to handle specific exceptions:
 *   - `NoElementInCacheException`
 *   - `ValidationException`
 *   - `ApiException`
 * - Sets up Swagger UI at the specified path.
 */
fun Application.configureHttp() {

    val facade by inject<FactStatisticsFacade>()
    val statisticsService by inject<StatisticsService>()

    routing {
        factsRoute(facade)
        adminRoute(statisticsService)
    }

    install(StatusPages) {
        exception<NoElementInCacheException> { call, cause ->
            call.respondText(text = "404: $cause", status = HttpStatusCode.NotFound)
        }
        exception<ValidationException> { call, cause ->
            call.respondText(text = "400: $cause", status = HttpStatusCode.BadRequest)
        }
        exception<ApiException> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        exception<ResponseException> { call, cause ->
            call.respondText(
                text = "503: ${cause.message}",
                status = HttpStatusCode.ServiceUnavailable
            )
        }
    }

    routing {
        swaggerUI(path = "swagger")
    }
}