package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.domain.ShortenedUrlFact

/**
 * Interface that provides functionality
 * for retrieving and managing facts and their associated statistics.
 */
interface FactStatisticsFacade {

    /**
     * Retrieves a random fact with its associated shortened URL.
     *
     * @return A [ShortenedUrlFact] object containing a random fact and its shortened URL.
     */
    suspend fun getRandomFact(): ShortenedUrlFact

    /**
     * Retrieves a fact identified by the given shortened URL.
     *
     * @param shortenedUrl The shortened URL associated with the fact.
     * @return The fact associated with the given shortened URL.
     */
    suspend fun getFactBy(shortenedUrl: String): Fact

    /**
     * Retrieves a collection of cached fact responses.
     *
     * @return A collection of [Fact] objects representing cached facts.
     */
    fun getCachedFacts(): Collection<Fact>
}