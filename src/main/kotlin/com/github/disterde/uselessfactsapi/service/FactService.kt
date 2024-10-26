package com.github.disterde.uselessfactsapi.service

import com.github.disterde.uselessfactsapi.domain.Fact

interface FactService {
    suspend fun getRandomFact(): Fact
    suspend fun getFactBy(shortenedUrl: String): Fact
    fun getCachedFacts(): Collection<Fact>
}