package com.github.disterde.uselessfactsapi.route

import com.github.disterde.uselessfactsapi.service.FactStatisticsFacade
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.factsRoute(
    facade: FactStatisticsFacade
) = route("/facts") {
    get {
        call.respond(facade.getCachedFacts())
    }

    post {
        call.respond(facade.getRandomFact())
    }

    route("/{shortenedUrl}") {
        get {
            call.parameters["shortenedUrl"]?.let {
                call.respond(facade.getFactBy(it))
            } ?: call.respond(BadRequest, "Invalid path parameter")
        }

        get("/redirect") {

        }
    }
}