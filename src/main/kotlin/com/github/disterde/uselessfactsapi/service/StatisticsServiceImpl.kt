package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.component.cache.Cache
import com.github.disterde.uselessfactsapi.component.cache.CacheImpl
import com.github.disterde.uselessfactsapi.domain.StatisticsResponse
import java.util.concurrent.atomic.AtomicInteger

/**
 * Implementation of the [StatisticsService] interface, providing logic
 * for managing and counting access statistics for shortened URLs.
 *
 * @property cache A cache system for storing access counts of URLs.
 */
class StatisticsServiceImpl(
    private val cache: Cache<String, AtomicInteger> = CacheImpl()
) : StatisticsService {

    /**
     * Saves the access count statistic for a given shortened URL.
     *
     * @param url The shortened URL whose initial access count is to be saved. Must be non-null.
     */
    override fun save(url: String) {
        cache.save(url, AtomicInteger(0))
    }

    /**
     * Increments the access count for the provided shortened URL.
     *
     * @param url The shortened URL whose access count is to be incremented.
     */
    override fun incrementAccessCount(url: String) {
        cache.getBy(url).incrementAndGet()
    }

    /**
     * Retrieves a collection of statistics related to shortened URL accesses.
     *
     * @return A collection of [StatisticsResponse] objects containing statistics data.
     */
    override fun getStatistics(): Collection<StatisticsResponse> {
        return cache.getEntries().map { StatisticsResponse(it.key, it.value.get()) }
    }
}