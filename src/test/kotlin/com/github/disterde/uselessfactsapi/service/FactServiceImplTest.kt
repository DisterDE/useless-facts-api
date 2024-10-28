package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.component.cache.CacheImpl
import com.github.disterde.uselessfactsapi.component.web.UselessFactsClient
import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.exception.NoElementInCacheException
import io.mockk.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FactServiceImplTest {

    private val client = mockk<UselessFactsClient>()
    private val cache = mockk<CacheImpl<String, Fact>>()
    private val service = FactServiceImpl(client, cache)

    @Test
    fun `should retrieve a random fact`() = runTest {
        coEvery { client.getRandomFact() } returns FACT
        every { cache.save(URL, any()) } just runs

        val fact = service.getRandomFact(URL)

        coVerify { client.getRandomFact() }
        verify { cache.save(URL, any()) }
        assertEquals(FACT, fact)
    }

    @Test
    fun `should retrieve a fact by id`() {
        every { cache.getBy(URL) } returns FACT

        val result = service.getFactBy(URL)

        assertEquals(FACT, result)
    }

    @Test
    fun `should throw exception when element is not found in cache`() {
        every { cache.getBy(URL) } throws NoElementInCacheException(URL)

        assertFailsWith<NoElementInCacheException> { service.getFactBy(URL) }
    }

    @Test
    fun `should return all facts from cache`() = runTest {
        every { cache.getAll() } returns CACHED_FACTS

        val result = service.getCachedFacts()

        assertEquals(result, CACHED_FACTS)
    }

    companion object {
        private const val URL = "test"
        private val FACT = Fact("123", "456")
        private val CACHED_FACTS = listOf(Fact("123", "456"))
    }
}
