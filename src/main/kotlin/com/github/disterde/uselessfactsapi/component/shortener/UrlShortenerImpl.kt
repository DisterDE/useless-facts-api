package com.github.disterde.uselessfactsapi.component.shortener

import com.github.disterde.uselessfactsapi.constants.UrlConstants.FACTS_BASE_PATH
import org.apache.commons.lang3.RandomStringUtils

class UrlShortenerImpl : UrlShortener {

    private val random = RandomStringUtils.secureStrong()

    override fun getShortUrl(): String {
        val shortenedUrl = random.nextAlphanumeric(LINK_LENGTH)
        return "$FACTS_BASE_PATH/$shortenedUrl"
    }

    companion object {
        private const val LINK_LENGTH = 5
    }
}