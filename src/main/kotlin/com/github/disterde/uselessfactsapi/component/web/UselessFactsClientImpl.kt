package com.github.disterde.uselessfactsapi.component.web

import com.github.disterde.uselessfactsapi.domain.Fact
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class UselessFactsClientImpl : UselessFactsClient {

    private val client = HttpClient(CIO) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override suspend fun getFact(): Fact {
        return client.get(RANDOM_FACT_URL).body()
    }

    override suspend fun getFactBy(url: String): Fact {
        validateUrl(url)
        return client.get(url).body()
    }

    private fun validateUrl(url: String) {
        if (!url.startsWith(BASE_URL)) error("Invalid url: $url, must start with $BASE_URL")
    }

    companion object {
        private const val BASE_URL = "https://uselessfacts.jsph.pl/api/v2/facts"
        private const val RANDOM_FACT_URL = "$BASE_URL/random?language=en"
    }
}