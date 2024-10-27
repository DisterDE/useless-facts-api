package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.domain.StatisticsResponse
import kotlin.test.*

class StatisticsServiceImplTest {

    private lateinit var statisticsService: StatisticsServiceImpl

    @BeforeTest
    fun setup() {
        statisticsService = StatisticsServiceImpl()
    }

    @Test
    fun `should save a new url`() {
        statisticsService.save(URL)

        val result = statisticsService.getStatistics()

        assertTrue { result.size == 1 }
        assertContains(result, RESPONSE)
    }

    @Test
    fun `should increment access count correctly`() {
        statisticsService.save(URL)
        statisticsService.incrementAccessCount(URL)

        val result = statisticsService.getStatistics().first()
        assertEquals(1, result.accessCount)
    }

    companion object {
        private const val URL = "test"
        private val RESPONSE = StatisticsResponse(URL, 0)
    }
}