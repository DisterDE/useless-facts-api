package com.github.disterde.uselessfactsapi.route

import com.github.disterde.uselessfactsapi.service.StatisticsService
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Configures an admin route for the application.
 *
 * Sets up routing under the "/admin" path, requiring authentication,
 * and provides an endpoint to retrieve statistics from the application.
 *
 * @param statisticsService An instance of [StatisticsService] used to obtain statistical data.
 */
fun Routing.adminRoute(statisticsService: StatisticsService) =
    route("/admin") {
        authenticate {
            get("/statistics") {
                call.respond(statisticsService.getStatistics())
            }
        }
    }