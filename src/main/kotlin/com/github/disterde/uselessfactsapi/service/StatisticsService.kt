package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.domain.StatisticsResponse

/**
 * Interface for managing statistics related to shortened URL accesses.
 */
interface StatisticsService {
    /**
     * Create the statistics related to the provided shortened URL.
     *
     * @param url The shortened URL whose statistics are to be saved.
     */
    fun save(url: String)

    /**
     * Increments the access count for the provided shortened URL.
     *
     * @param url The shortened URL whose access count is to be incremented.
     */
    fun incrementAccessCount(url: String)

    /**
     * Retrieves a collection of statistics related to shortened URL accesses.
     *
     * @return A collection of [StatisticsResponse] objects containing statistics data.
     */
    fun getStatistics(): Collection<StatisticsResponse>
}
