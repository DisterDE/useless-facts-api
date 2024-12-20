package com.github.disterde.uselessfactsapi.route

import com.github.disterde.uselessfactsapi.constants.UrlConstants.FACTS_BASE_PATH
import com.github.disterde.uselessfactsapi.service.FactStatisticsFacade
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Configures the routing for facts-related API endpoints.
 *
 * This method sets up various routes under the "/facts" base path:
 * - `GET /facts` - Retrieves cached facts.
 * - `POST /facts` - Retrieves a random fact.
 * - `GET /facts/{shortenedUrl}` - Retrieves a specific fact by its shortened URL.
 * - `GET /facts/{shortenedUrl}/redirect` - Redirects to the permanent link of a specific fact.
 *
 * @param facade An instance of [FactStatisticsFacade] used to handle fact-related operations and data retrieval.
 */
fun Routing.factsRoute(
    facade: FactStatisticsFacade
) = route(FACTS_BASE_PATH) {

    get { call.respond(facade.getCachedFacts()) }

    post { call.respond(facade.getRandomFact()) }

    route("/{shortenedUrl}") {
        get {
            val shortenedUrl = call.parameters["shortenedUrl"]
            val fact = facade.getFactBy("$FACTS_BASE_PATH/$shortenedUrl")
            call.respond(fact)
        }

        get("/redirect") {
            val shortenedUrl = call.parameters["shortenedUrl"]
            val fact = facade.getFactBy("$FACTS_BASE_PATH/$shortenedUrl")
            call.respondRedirect(fact.originalPermalink)
        }
    }
}
