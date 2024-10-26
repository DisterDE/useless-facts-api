package com.github.disterde.uselessfactsapi.route

import com.github.disterde.uselessfactsapi.service.StatisticsService
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.adminRoute(statisticsService: StatisticsService) =
    route("/admin") {
        get("/statistics") {
            call.respond(statisticsService.getStatistics())
        }
    }