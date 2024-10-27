package com.github.disterde.uselessfactsapi.route

import com.github.disterde.uselessfactsapi.domain.StatisticsResponse
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
import io.mockk.every
import io.mockk.mockk
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalEncodingApi::class, ExperimentalSerializationApi::class)
class AdminRouteTest {

    private val statisticsService = mockk<StatisticsService>()
    private val factStatisticsFacade = mockk<FactStatisticsFacade>()

    @Test
    fun testGetAdminStatistics() = testApplication {
        application {
            install(Koin) {
                modules(module {
                    single { statisticsService }
                    single { factStatisticsFacade }
                })
            }
            configureSecurity()
            configureHttp()
            configureSerialization()
        }

        every { statisticsService.getStatistics() } returns STATISTICS

        val client = createClient {
            install(ContentNegotiation) {
                json(json = Json {
                    namingStrategy = JsonNamingStrategy.SnakeCase
                })
            }
        }

        client.get("/admin/statistics") {
            header(HttpHeaders.Authorization, "Basic ${Base64.encode("admin:admin".toByteArray())}")
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(STATISTICS, body(typeInfo<List<StatisticsResponse>>()))
        }
    }

    companion object {
        private val STATISTICS = listOf(StatisticsResponse("123", 1))
    }
}
