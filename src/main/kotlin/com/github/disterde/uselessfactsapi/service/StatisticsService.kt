package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.domain.StatisticsResponse

interface StatisticsService {
    fun save(url: String)
    fun incrementAccessCount(url: String)
    fun getStatistics(): Collection<StatisticsResponse>
}