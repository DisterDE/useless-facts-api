package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.component.cache.Cache
import com.github.disterde.uselessfactsapi.component.cache.CacheImpl
import com.github.disterde.uselessfactsapi.component.web.UselessFactsClient
import com.github.disterde.uselessfactsapi.domain.Fact

/**
 * Implementation of the `FactService` interface that provides methods for retrieving, caching,
 * and managing facts. This class makes use of an external client to fetch facts and a local
 * cache to store them.
 *
 * @property client An instance of `UselessFactsClient` used to fetch facts from an external service.
 * @property cache A cache implementation used to store and retrieve facts, keyed by their shortened URLs.
 */
class FactServiceImpl(
    private val client: UselessFactsClient,
    private val cache: Cache<String, Fact> = CacheImpl()
) : FactService {

    /**
     * Fetches a random fact from the external service and associates it with a given shortened URL. The fact
     * is also saved in the cache with the shortened URL as the key.
     *
     * @param shortenedUrl The shortened URL to associate with the fetched fact.
     * @return A `Fact` object containing the random fact and its associated permanent link.
     */
    override suspend fun getRandomFact(shortenedUrl: String): Fact {
        return client.getFact().also { cache.save(shortenedUrl, it) }
    }

    /**
     * Retrieves a fact associated with the given shortened URL.
     *
     * @param shortenedUrl The shortened URL to retrieve the associated fact.
     * @return A `Fact` object containing the factual information and its associated permanent link.
     */
    override suspend fun getFactBy(shortenedUrl: String): Fact {
        val fact = cache.getBy(shortenedUrl)
        return client.getFactBy(fact.permalink)
    }

    /**
     * Retrieves all facts that are currently stored in the cache.
     *
     * @return A collection of cached `Fact` objects.
     */
    override fun getCachedFacts(): Collection<Fact> {
        return cache.getAll()
    }
}