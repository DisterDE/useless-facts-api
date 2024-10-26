package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.component.shortener.UrlShortener
import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.domain.FactResponse
import com.github.disterde.uselessfactsapi.domain.ShortenedUrlFact

class FactStatisticsFacadeImpl(
    private val shortener: UrlShortener,
    private val factService: FactService,
    private val statisticsService: StatisticsService
) : FactStatisticsFacade {

    override suspend fun getRandomFact(): ShortenedUrlFact {
        val url = shortener.getShortUrl()
        val fact = factService.getRandomFact(url)
        return fact.toShortenedResponse(url).also {
            statisticsService.save(url)
        }
    }

    override suspend fun getFactBy(shortenedUrl: String): Fact {
        return factService.getFactBy(shortenedUrl).also {
            statisticsService.incrementAccessCount(shortenedUrl)
        }
    }

    override fun getCachedFacts(): Collection<FactResponse> {
        return factService.getCachedFacts().map { it.toResponse() }
    }
}