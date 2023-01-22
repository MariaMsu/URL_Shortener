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

    public fun setUrl(longUrl: String): String? {
        var shortUrl: String;
        for (seed in 0..10) {
            // try to find a free spot for the new string
            shortUrl = md5(longUrl, seed.toByte());
            if (!data.containsKey(shortUrl)) {
                data[shortUrl] = longUrl
                return shortUrl
            }
        }
        return null
    }

    fun md5(input: String, seed: Byte): String {
        // hash input string and map it into a short string
        // val encodedInt = BigInteger(1, hasher.digest(input.toByteArray()))
        val encodedInt = BigInteger(1, hasher.digest(input.toByteArray() + seed))
        return encodedInt.toString(36).takeLast(10);
    }

    final var data: MutableMap<String, String> = mutableMapOf()
        private set // the setter is private and has the default implementation

    private val hasher = MessageDigest.getInstance("MD5")
}
