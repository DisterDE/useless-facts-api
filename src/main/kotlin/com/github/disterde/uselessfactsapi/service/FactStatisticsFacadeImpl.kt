package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.component.shortener.UrlShortener
import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.domain.FactResponse
import com.github.disterde.uselessfactsapi.domain.ShortenedUrlFact

/**
 * Implementation of the `FactStatisticsFacade` interface that provides functionality
 * for retrieving and managing facts and their associated statistics.
 *
 * @property shortener An instance of `UrlShortener` for URL shortening functionality.
 * @property factService An instance of `FactService` for retrieving and caching facts.
 * @property statisticsService An instance of `StatisticsService` for managing fact access statistics.
 */
class FactStatisticsFacadeImpl(
    private val shortener: UrlShortener,
    private val factService: FactService,
    private val statisticsService: StatisticsService
) : FactStatisticsFacade {

    /**
     * Retrieves a random fact, creates a shortened URL, associates them and saves the generated URL's statistics.
     *
     * @return A [ShortenedUrlFact] object containing the random fact and its shortened URL.
     */
    override suspend fun getRandomFact(): ShortenedUrlFact {
        val url = shortener.getShortUrl()
        val fact = factService.getRandomFact(url)
        return fact.toShortenedResponse(url).also {
            statisticsService.save(url)
        }
    }

    /**
     * Retrieves the fact associated with the given shortened URL
     * and increments the access count in the fact's statistics.
     *
     * @param shortenedUrl The shortened URL to retrieve the associated fact.
     * @return The `Fact` object containing the factual information and its permanent link.
     */
    override suspend fun getFactBy(shortenedUrl: String): Fact {
        return factService.getFactBy(shortenedUrl).also {
            statisticsService.incrementAccessCount(shortenedUrl)
        }
    }

    /**
     * Retrieves a collection of cached fact responses.
     *
     * @return A collection of [FactResponse] objects representing cached facts.
     */
    override fun getCachedFacts(): Collection<FactResponse> {
        return factService.getCachedFacts().map { it.toResponse() }
    }
}