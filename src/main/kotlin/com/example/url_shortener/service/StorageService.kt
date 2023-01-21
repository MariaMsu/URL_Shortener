package com.example.url_shortener.service

import org.springframework.stereotype.Service

@Service
class StorageService() {
    // map sort_url to long_url

    public fun getUrl(shortUrl: String): String? {
        return data[shortUrl.toInt()]
    }

    public fun setUrl(longUrl: String): Int {
        val shortUrl = hashString(longUrl)
        data[shortUrl] = longUrl
        return shortUrl
    }

    private fun hashString(input: String): Int {
        // TODO
        // https://www.samclarke.com/kotlin-hash-strings/
        return input[0].code;
    }

    private var data: MutableMap<Int, String> = mutableMapOf()
}

