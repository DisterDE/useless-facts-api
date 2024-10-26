package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.domain.FactResponse
import com.github.disterde.uselessfactsapi.domain.ShortenedUrlFact

interface FactStatisticsFacade {
    suspend fun getRandomFact(): ShortenedUrlFact
    suspend fun getFactBy(shortenedUrl: String): Fact
    fun getCachedFacts(): Collection<FactResponse>
}