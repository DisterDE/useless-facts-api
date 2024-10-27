package com.github.disterde.uselessfactsapi.component.cache

import com.github.disterde.uselessfactsapi.exception.ElementAlreadyInCacheException
import com.github.disterde.uselessfactsapi.exception.NoElementInCacheException
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class CacheImplTest {


    lateinit var cache: CacheImpl<String, Int>

    @BeforeTest
    fun setup() {
        cache = CacheImpl()
    }

    @Test
    fun `should save and retrieve entity correctly`() {
        cache.save(KEY, VALUE)

        assertEquals(VALUE, cache.getBy(KEY))
    }

    @Test
    fun `should throw ElementAlreadyInCacheException when entity has already been saved`() {
        cache.save(KEY, VALUE)

        assertFailsWith<ElementAlreadyInCacheException>(
            message = "Element with key $KEY is already in cache"
        ) { cache.save(KEY, VALUE) }
    }

    @Test
    fun `should retrieve entity correctly`() {
        cache.save(KEY, VALUE)
        cache.save(KEY2, VALUE2)

        assertEquals(VALUE, cache.getBy(KEY))
        assertEquals(VALUE2, cache.getBy(KEY2))
    }

    @Test
    fun `should throw NoElementInCacheException when entity is not found by key`() {
        assertFailsWith<NoElementInCacheException>(
            message = "Element is not found in cache by $KEY"
        ) { cache.getBy(KEY) }
    }

    @Test
    fun `should return all saved values when getAll is called`() {
        cache.save(KEY, VALUE)
        cache.save(KEY2, VALUE2)

        assertThat(cache.getAll())
            .hasSameElementsAs(CACHE_MAP.values)
    }

    @Test
    fun `should return all entries when getEntries is called`() {
        cache.save(KEY, VALUE)
        cache.save(KEY2, VALUE2)

        assertThat(cache.getEntries().entries)
            .hasSameElementsAs(CACHE_MAP.entries)
    }

    companion object {
        private const val KEY = "key"
        private const val KEY2 = "key2"
        private const val VALUE = 123
        private const val VALUE2 = 1234
        private val CACHE_MAP = mapOf(KEY to VALUE, KEY2 to VALUE2)
    }
}