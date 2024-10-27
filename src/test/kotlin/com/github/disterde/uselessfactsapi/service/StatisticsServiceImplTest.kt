package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.component.cache.CacheImpl
import com.github.disterde.uselessfactsapi.domain.StatisticsResponse
import io.mockk.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.Test
import kotlin.test.assertContentEquals

class StatisticsServiceImplTest {

    private val cache = mockk<CacheImpl<String, AtomicInteger>>()
    private val statisticsService = StatisticsServiceImpl(cache)

    @Test
    fun `should save a new url`() {
        every { cache.save(URL, any<AtomicInteger>()) } just runs

        statisticsService.save(URL)

        verify { cache.save(URL, any<AtomicInteger>()) }
    }

    @Test
    fun `should increment access count correctly`() {
        every { cache.getBy(URL) } returns VALUE_MOCK

        statisticsService.incrementAccessCount(URL)

        verify { VALUE_MOCK.incrementAndGet() }
    }

    @Test
    fun `should return all entries from cache`() {
        every { cache.getEntries() } returns CACHE

        val result = statisticsService.getStatistics()

        verify { cache.getEntries() }
        assertContentEquals(RESPONSES, result)
    }

    companion object {
        private const val URL = "test"
        private val CACHE = mapOf(URL to AtomicInteger(0))
        private val RESPONSES = listOf(StatisticsResponse(URL, 0))
        private val VALUE_MOCK = mockk<AtomicInteger>(relaxed = true)
    }
}