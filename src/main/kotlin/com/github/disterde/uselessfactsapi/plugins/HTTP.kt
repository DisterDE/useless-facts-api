package com.github.disterde.uselessfactsapi.plugins

import com.github.disterde.uselessfactsapi.route.adminRoute
import com.github.disterde.uselessfactsapi.route.factsRoute
import com.github.disterde.uselessfactsapi.service.FactStatisticsFacade
import com.github.disterde.uselessfactsapi.service.StatisticsService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureHttp() {

    val facade by inject<FactStatisticsFacade>()
    val statisticsService by inject<StatisticsService>()

    routing {
        factsRoute(facade)
        adminRoute(statisticsService)
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        swaggerUI(path = "swagger")
    }
}