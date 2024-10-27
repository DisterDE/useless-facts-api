package com.github.disterde.uselessfactsapi.component.web

import com.github.disterde.uselessfactsapi.constants.UrlConstants.EXTERNAL_FACTS_URL
import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.exception.ValidationException
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
import kotlin.test.assertFailsWith

class UselessFactsClientImplTest {

    private val client = HttpClient(MockEngine { request ->
        respond(
            content = Json.encodeToString(FACT),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")

        )
    }) {
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
    }

    private val uselessFactsClient = UselessFactsClientImpl(client)

    @Test
    fun `should call http client when retrieving a random fact`() = runTest {
        val result = uselessFactsClient.getFact()
        assertEquals(result, FACT)
    }

    @Test
    fun `should call http client when retrieving a fact by correct id`() = runTest {
        val result = uselessFactsClient.getFactBy("$EXTERNAL_FACTS_URL/123")
        assertEquals(result, FACT)
    }

    @Test
    fun `should throw ValidationException when retrieving a fact by incorrect id`() = runTest {
        val url = "123"

        assertFailsWith<ValidationException>(
            message = "Invalid url: $url, must start with $EXTERNAL_FACTS_URL"
        ) { uselessFactsClient.getFactBy(url) }
    }

    companion object {
        private val FACT = Fact("123", "456")
    }
}