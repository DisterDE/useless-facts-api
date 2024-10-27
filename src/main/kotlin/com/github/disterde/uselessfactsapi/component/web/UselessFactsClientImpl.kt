package com.github.disterde.uselessfactsapi.component.web

import com.github.disterde.uselessfactsapi.constants.UrlConstants.EXTERNAL_RANDOM_FACT_URL
import com.github.disterde.uselessfactsapi.domain.Fact
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

/**
 * A client implementation for retrieving and managing useless facts using an HTTP client.
 *
 * @property client The HTTP client used to make requests to external services.
 */
class UselessFactsClientImpl(
    private val client: HttpClient
) : UselessFactsClient {

    /**
     * Retrieves a random fact.
     *
     * @return A `Fact` object containing the fact text and its permanent link.
     */
    override suspend fun getFact(): Fact {
        return client.get(EXTERNAL_RANDOM_FACT_URL).body()
    }
}