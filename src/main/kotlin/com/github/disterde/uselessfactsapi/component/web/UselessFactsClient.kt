package com.github.disterde.uselessfactsapi.component.web

import com.github.disterde.uselessfactsapi.domain.Fact

/**
 * Interface representing a client for retrieving and managing useless facts.
 */
interface UselessFactsClient {
    /**
     * Retrieves a random fact.
     *
     * @return A `Fact` object containing the fact and its permanent link.
     */
    suspend fun getFact(): Fact

    /**
     * Retrieves a fact from the given URL.
     *
     * @param url The URL to retrieve the fact from.
     * @return A `Fact` object containing the retrieved fact and its permanent link.
     */
    suspend fun getFactBy(url: String): Fact
}