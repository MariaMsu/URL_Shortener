package com.example.url_shortener.service

import org.springframework.stereotype.Service
import java.math.BigInteger
import java.security.MessageDigest

@Service
class StorageService() {
    // map sort_url to long_url

    public fun getUrl(shortUrl: String): String? {
        return data[shortUrl]
    }

    public fun setUrl(longUrl: String): String {
        val shortUrl = md5(longUrl)
        data[shortUrl] = longUrl
        return shortUrl
    }

    fun md5(input: String): String {
        // https://stackoverflow.com/questions/64171624/how-to-generate-an-md5-hash-in-kotlin
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    final var data: MutableMap<String, String> = mutableMapOf()
        private set // the setter is private and has the default implementation

}

