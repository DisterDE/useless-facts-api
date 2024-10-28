package com.github.disterde.uselessfactsapi.route

import com.github.disterde.uselessfactsapi.constants.UrlConstants.FACTS_BASE_PATH
import com.github.disterde.uselessfactsapi.domain.Fact
import com.github.disterde.uselessfactsapi.domain.ShortenedUrlFact
import com.github.disterde.uselessfactsapi.plugins.configureHttp
import com.github.disterde.uselessfactsapi.plugins.configureSecurity
import com.github.disterde.uselessfactsapi.plugins.configureSerialization
import com.github.disterde.uselessfactsapi.service.FactStatisticsFacade
import com.github.disterde.uselessfactsapi.service.StatisticsService
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import io.ktor.util.reflect.*
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalSerializationApi::class)
class FactsRouteTest {

    private val statisticsService = mockk<StatisticsService>()
    private val facade = mockk<FactStatisticsFacade>()

    @Test
    fun testGetFacts() = testApplication {
        application {
            install(Koin) {
                modules(module {
                    single { facade }
                    single { statisticsService }
                })
            }
            configureSecurity()
            configureHttp()
            configureSerialization()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json(json = Json {
                    namingStrategy = JsonNamingStrategy.SnakeCase
                })
            }
        }

        every { facade.getCachedFacts() } returns FACTS

        client.get(FACTS_BASE_PATH).apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(FACTS, body(typeInfo<List<Fact>>()))
        }
    }

    @Test
    fun testPostFacts() = testApplication {
        application {
            install(Koin) {
                modules(module {
                    single { facade }
                    single { statisticsService }
                })
            }
            configureSecurity()
            configureHttp()
            configureSerialization()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json(json = Json {
                    namingStrategy = JsonNamingStrategy.SnakeCase
                })
            }
        }

        coEvery { facade.getRandomFact() } returns SHORTENED_URL_FACT

        client.post(FACTS_BASE_PATH).apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(SHORTENED_URL_FACT, body(typeInfo<ShortenedUrlFact>()))
        }
    }

    @Test
    fun testGetFactsShortenedUrl() = testApplication {
        application {
            install(Koin) {
                modules(module {
                    single { facade }
                    single { statisticsService }
                })
            }
            configureSecurity()
            configureHttp()
            configureSerialization()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json(json = Json {
                    namingStrategy = JsonNamingStrategy.SnakeCase
                })
            }
        }

        coEvery { facade.getFactBy("$FACTS_BASE_PATH/$SHORTENED_URL") } returns FACT

        client.get("$FACTS_BASE_PATH/$SHORTENED_URL").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(FACT, body(typeInfo<Fact>()))
        }
    }

    @Test
    fun testGetFactsShortenedUrlRedirect() = testApplication {
        application {
            install(Koin) {
                modules(module {
                    single { facade }
                    single { statisticsService }
                })
            }
            configureSecurity()
            configureHttp()
            configureSerialization()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json(json = Json {
                    namingStrategy = JsonNamingStrategy.SnakeCase
                })
            }
            followRedirects = false
        }

        coEvery { facade.getFactBy("$FACTS_BASE_PATH/$SHORTENED_URL") } returns FACT

        client.get("$FACTS_BASE_PATH/$SHORTENED_URL/redirect").apply {
            assertEquals(HttpStatusCode.Found, status)
            assertEquals(FACT.originalPermalink, headers[HttpHeaders.Location])
        }
    }

    companion object {
        private const val SHORTENED_URL = "url"
        private const val PERMANENT_URL = "https://test.com"
        private const val FACT_TEXT = "text"
        private val FACT = Fact(FACT_TEXT, PERMANENT_URL)
        private val SHORTENED_URL_FACT = ShortenedUrlFact(FACT_TEXT, SHORTENED_URL)
        private val FACTS = listOf(FACT)
    }
}
