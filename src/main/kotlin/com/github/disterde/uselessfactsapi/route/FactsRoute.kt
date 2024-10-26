package com.github.disterde.uselessfactsapi.route

import com.github.disterde.uselessfactsapi.constants.UrlConstants.FACTS_BASE_PATH
import com.github.disterde.uselessfactsapi.service.FactStatisticsFacade
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.factsRoute(
    facade: FactStatisticsFacade
) = route(FACTS_BASE_PATH) {
    get {
        call.respond(facade.getCachedFacts())
    }

    post {
        call.respond(facade.getRandomFact())
    }

    route("/{shortenedUrl}") {
        get {
            call.parameters["shortenedUrl"]?.let {
                call.respond(facade.getFactBy("$FACTS_BASE_PATH/$it"))
            } ?: call.respond(BadRequest, "Invalid path parameter")
        }

        get("/redirect") {
            call.parameters["shortenedUrl"]?.let {
                val fact = facade.getFactBy("$FACTS_BASE_PATH/$it")
                call.respondRedirect(fact.permalink)
            } ?: call.respond(BadRequest, "Invalid path parameter")
        }
    }
}