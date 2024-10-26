package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.domain.StatisticResponse

interface StatisticsService {
    fun save(url: String)
    fun incrementAccessCount(url: String)
    fun getStatistics(): Collection<StatisticResponse>
}