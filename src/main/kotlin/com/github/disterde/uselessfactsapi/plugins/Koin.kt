package com.github.disterde.uselessfactsapi.plugins

import com.github.disterde.uselessfactsapi.component.shortener.UrlShortener
import com.github.disterde.uselessfactsapi.component.shortener.UrlShortenerImpl
import com.github.disterde.uselessfactsapi.component.web.UselessFactsClient
import com.github.disterde.uselessfactsapi.component.web.UselessFactsClientImpl
import com.github.disterde.uselessfactsapi.service.*
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(services)
    }
}

private val services = module {
    single<UrlShortener> { UrlShortenerImpl() }
    single<FactService> { FactServiceImpl(get()) }
    single<UselessFactsClient> { UselessFactsClientImpl() }
    single<StatisticsService> { StatisticsServiceImpl() }
    single<FactStatisticsFacade> { FactStatisticsFacadeImpl(get(), get(), get()) }
}