package com.github.disterde.uselessfactsapi.plugins

import com.github.disterde.uselessfactsapi.component.cache.Cache
import com.github.disterde.uselessfactsapi.component.cache.CacheImpl
import com.github.disterde.uselessfactsapi.component.shortener.UrlShortener
import com.github.disterde.uselessfactsapi.component.shortener.UrlShortenerImpl
import com.github.disterde.uselessfactsapi.component.web.UselessFactsClient
import com.github.disterde.uselessfactsapi.component.web.UselessFactsClientImpl
import com.github.disterde.uselessfactsapi.service.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

/**
 * Configures the Koin dependency injection framework for the application.
 *
 * This function installs and configures Koin with the SLF4J logger and
 * specified modules required for the application's services.
 */
fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(services)
    }
}

private val services = module {
    factory<Cache<Any, Any>> { CacheImpl() }
    single<UrlShortener> { UrlShortenerImpl() }
    single<FactService> { FactServiceImpl(get(), get()) }
    single<StatisticsService> { StatisticsServiceImpl(get()) }
    single<UselessFactsClient> { UselessFactsClientImpl(get()) }
    single<FactStatisticsFacade> { FactStatisticsFacadeImpl(get(), get(), get()) }
    single<HttpClient> {
        HttpClient(CIO) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }
}
