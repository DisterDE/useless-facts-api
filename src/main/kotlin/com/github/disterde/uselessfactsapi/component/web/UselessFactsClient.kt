package com.github.disterde.uselessfactsapi.component.web

import com.github.disterde.uselessfactsapi.domain.Fact

/**
 * Interface for retrieving random facts from an external source.
 */
interface UselessFactsClient {

    /**
     * Retrieves a random fact.
     *
     * @return A `Fact` object containing the fact and its permanent link.
     */
    suspend fun getRandomFact(): Fact
}