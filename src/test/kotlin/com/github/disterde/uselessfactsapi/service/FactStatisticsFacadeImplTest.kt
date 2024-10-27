package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.component.shortener.UrlShortener
import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.domain.ShortenedUrlFact
import io.mockk.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FactStatisticsFacadeImplTest {

    private val shortener = mockk<UrlShortener>()
    private val factService = mockk<FactService>()
    private val statisticsService = mockk<StatisticsService>()
    private val sut = FactStatisticsFacadeImpl(shortener, factService, statisticsService)

    @Test
    fun `should call all services correctly to get a random fact`() = runTest {
        every { shortener.getShortUrl() } returns URL
        coEvery { factService.getRandomFact(URL) } returns FACT
        every { statisticsService.save(URL) } just runs

        val fact = sut.getRandomFact()

        assertEquals(SHORTENED_URL_FACT, fact)
        verify { shortener.getShortUrl() }
        coVerify { factService.getRandomFact(URL) }
        verify { statisticsService.save(URL) }
    }

    @Test
    fun `should call fact service and increment access count by short url`() = runTest {
        every { factService.getFactBy(URL) } returns FACT
        every { statisticsService.incrementAccessCount(URL) } just runs

        val result = sut.getFactBy(URL)

        assertEquals(FACT, result)
        verify { factService.getFactBy(URL) }
        verify { statisticsService.incrementAccessCount(URL) }
    }

    @Test
    fun `should return all facts and wrap them in response`() {
        every { factService.getCachedFacts() } returns CACHED_FACTS

        val result = sut.getCachedFacts()

        assertEquals(result, CACHED_FACTS)
    }

    companion object {
        private const val URL = "test"
        private val FACT = Fact("123", "456")
        private val SHORTENED_URL_FACT = ShortenedUrlFact("123", URL)
        private val CACHED_FACTS = listOf(FACT)
    }
}