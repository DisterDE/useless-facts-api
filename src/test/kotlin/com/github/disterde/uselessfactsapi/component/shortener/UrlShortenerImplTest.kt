package com.github.disterde.uselessfactsapi.component.shortener

import com.github.disterde.uselessfactsapi.component.shortener.UrlShortenerImpl.Companion.LINK_LENGTH
import com.github.disterde.uselessfactsapi.constants.UrlConstants.FACTS_BASE_PATH
import kotlin.test.*

class UrlShortenerImplTest {

    private lateinit var urlShortener: UrlShortenerImpl

    @BeforeTest
    fun setup() {
        urlShortener = UrlShortenerImpl()
    }

    @Test
    fun `should generate URL of correct length`() {
        val url = urlShortener.getShortUrl()
        val result = url.substringAfterLast('/')

        assertEquals(result.length, LINK_LENGTH)
    }

    @Test
    fun `should generate different URLs of the same length`() {
        val url = urlShortener.getShortUrl()
        val url2 = urlShortener.getShortUrl()

        assertNotEquals(url, url2)
        assertEquals(url.length, url2.length)
    }

    @Test
    fun `should start from the facts base path`() {
        val shortUrl = urlShortener.getShortUrl()

        assertTrue(shortUrl.startsWith(FACTS_BASE_PATH))
    }

    @Test
    fun `should generate different urls`() {
        val shortUrls = setOf(
            urlShortener.getShortUrl(),
            urlShortener.getShortUrl(),
            urlShortener.getShortUrl()
        )

        assertEquals(3, shortUrls.size)
    }
}
