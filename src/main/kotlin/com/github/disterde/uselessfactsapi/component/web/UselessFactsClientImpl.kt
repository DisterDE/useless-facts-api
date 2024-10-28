package com.github.disterde.uselessfactsapi.component.web

import com.github.disterde.uselessfactsapi.constants.UrlConstants.EXTERNAL_RANDOM_FACT_URL
import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.domain.FactApiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

/**
 * Implementation of the interface for retrieving useless facts.
 *
 * @param client The HTTP client used to make requests to the useless facts API.
 */
class UselessFactsClientImpl(
    private val client: HttpClient
) : UselessFactsClient {

    /**
     * Retrieves a random fact from an external source.
     *
     * @return A `Fact` object containing the fact text and its permanent link.
     */
    override suspend fun getRandomFact(): Fact {
        return client.get(EXTERNAL_RANDOM_FACT_URL)
            .body<FactApiResponse>()
            .toFact()
    }
}
