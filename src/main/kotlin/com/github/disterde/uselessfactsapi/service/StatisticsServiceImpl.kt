package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.component.cache.Cache
import com.github.disterde.uselessfactsapi.component.cache.CacheImpl
import com.github.disterde.uselessfactsapi.domain.StatisticResponse
import java.util.concurrent.atomic.AtomicInteger

class StatisticsServiceImpl(
    private val cache: Cache<String, AtomicInteger> = CacheImpl()
) : StatisticsService {

    override fun save(url: String) {
        cache.save(url, AtomicInteger(0))
    }

    override fun incrementAccessCount(url: String) {
        cache.getBy(url).incrementAndGet()
    }

    override fun getStatistics(): Collection<StatisticResponse> {
        return cache.getEntries().map { StatisticResponse(it.key, it.value.get()) }
    }
}