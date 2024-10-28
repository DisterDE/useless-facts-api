package com.github.disterde.uselessfactsapi.component.web

import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.domain.FactApiResponse
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class UselessFactsClientImplTest {

    private val client = HttpClient(MockEngine {
        respond(
            content = Json.encodeToString(FACT_API_RESPONSE),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")

        )
    }) {
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
    }

    private val uselessFactsClient = UselessFactsClientImpl(client)

    @Test
    fun `should call http client when retrieving a random fact`() = runTest {
        val result = uselessFactsClient.getRandomFact()
        assertEquals(result, FACT)
    }

    companion object {
        private val FACT = Fact("123", "456")
        private val FACT_API_RESPONSE = FactApiResponse("123", "456")
    }
}