package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.component.cache.Cache
import com.github.disterde.uselessfactsapi.component.cache.CacheImpl
import com.github.disterde.uselessfactsapi.component.web.UselessFactsClient
import com.github.disterde.uselessfactsapi.domain.Fact

class FactServiceImpl(
    private val client: UselessFactsClient,
    private val cache: Cache<String, Fact> = CacheImpl()
) : FactService {

    override suspend fun getRandomFact(): Fact {
        return client.getFact()
    }

    override suspend fun getFactBy(shortenedUrl: String): Fact {
        val fact = cache.getBy(shortenedUrl)
        return client.getFactBy(fact.permalink)
    }

    override fun getCachedFacts(): Collection<Fact> {
        return cache.getAll()
    }
}