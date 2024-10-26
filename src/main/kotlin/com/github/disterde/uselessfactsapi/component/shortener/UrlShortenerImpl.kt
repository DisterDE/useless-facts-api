package com.github.disterde.uselessfactsapi.component.shortener

import java.util.concurrent.atomic.AtomicInteger

class UrlShortenerImpl : UrlShortener {

    private val counter = AtomicInteger(1)

    override fun getShortUrl(): String {
        val shortenedUrl = counter.getAndIncrement().toString(36)
        return "$SHORT_URL_BASE/$shortenedUrl"
    }

    companion object {
        private const val SHORT_URL_BASE = "/facts"
    }
}