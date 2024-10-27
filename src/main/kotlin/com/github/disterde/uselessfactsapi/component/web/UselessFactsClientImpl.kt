package com.github.disterde.uselessfactsapi.component.web

import com.github.disterde.uselessfactsapi.constants.UrlConstants.EXTERNAL_FACTS_URL
import com.github.disterde.uselessfactsapi.constants.UrlConstants.EXTERNAL_RANDOM_FACT_URL
import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.exception.ValidationException
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

    /**
     * Retrieves a fact from the given URL.
     *
     * @param url The URL to retrieve the fact from.
     * @return A `Fact` object containing the retrieved fact and its permanent link.
     */
    override suspend fun getFactBy(url: String): Fact {
        validateUrl(url)
        return client.get(url).body()
    }

    /**
     * Validates the given URL to ensure it starts with the expected external facts URL.
     *
     * @param url The URL to validate.
     * @throws ValidationException if the URL does not start with the external facts api url.
     */
    private fun validateUrl(url: String) {
        if (!url.startsWith(EXTERNAL_FACTS_URL)) throw ValidationException("Invalid url: $url, must start with $EXTERNAL_FACTS_URL")
    }
}