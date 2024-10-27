package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.domain.Fact

/**
 * Service interface to handle operations related to retrieving and caching facts.
 */
interface FactService {
    /**
     * Fetches a random fact from the service and associates it with a shortened URL.
     *
     * @param shortenedUrl The shortened URL to associate with the fetched fact.
     * @return A `Fact` object containing the random fact and its associated permalink.
     */
    suspend fun getRandomFact(shortenedUrl: String): Fact

    /**
     * Retrieves a fact associated with the given shortened URL.
     *
     * @param shortenedUrl The shortened URL to retrieve the associated fact.
     * @return A `Fact` object containing the factual information and its associated permanent link.
     */
    fun getFactBy(shortenedUrl: String): Fact

    /**
     * Retrieves all facts that are currently stored in the cache.
     *
     * @return A collection of cached `Fact` objects.
     */
    fun getCachedFacts(): Collection<Fact>
}