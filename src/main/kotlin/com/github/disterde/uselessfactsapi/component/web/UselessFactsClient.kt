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
}