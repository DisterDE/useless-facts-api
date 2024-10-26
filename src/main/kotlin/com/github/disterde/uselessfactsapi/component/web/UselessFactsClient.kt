package com.github.disterde.uselessfactsapi.component.web

import com.github.disterde.uselessfactsapi.domain.Fact

interface UselessFactsClient {
    suspend fun getFact(): Fact
    suspend fun getFactBy(url: String): Fact
}