package com.github.disterde.uselessfactsapi.component.shortener

import com.github.disterde.uselessfactsapi.constants.UrlConstants.SHORT_URL_BASE
import org.apache.commons.lang3.RandomStringUtils

class UrlShortenerImpl : UrlShortener {

    private val random = RandomStringUtils.secureStrong()

    override fun getShortUrl(): String {
        val shortenedUrl = random.nextAlphanumeric(5)
        return "$SHORT_URL_BASE/$shortenedUrl"
    }
}