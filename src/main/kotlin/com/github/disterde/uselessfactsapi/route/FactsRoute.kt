package com.github.disterde.uselessfactsapi.route

import com.github.disterde.uselessfactsapi.constants.UrlConstants.SHORT_URL_BASE
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
                call.respond(facade.getFactBy("$SHORT_URL_BASE/$it"))
            } ?: call.respond(BadRequest, "Invalid path parameter")
        }

        get("/redirect") {
            call.parameters["shortenedUrl"]?.let {
                val fact = facade.getFactBy("$SHORT_URL_BASE/$it")
                call.respondRedirect(fact.permalink)
            } ?: call.respond(BadRequest, "Invalid path parameter")
        }
    }
}