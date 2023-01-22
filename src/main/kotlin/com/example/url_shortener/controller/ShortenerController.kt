package com.example.url_shortener.controller

import com.example.url_shortener.service.StorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ShortenerController {

    @Autowired
    lateinit var storageService: StorageService;

    @GetMapping("/set-long-url/{longUrl}")
    fun setLongUrl(@PathVariable longUrl: String): ResponseEntity<String> {
        val result = storageService.setUrl(longUrl);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{shortUrl}")
    fun getShortUrl(@PathVariable shortUrl: String): ResponseEntity<String> {
        val result = storageService.getUrl(shortUrl)
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-data")
    fun getData(): ResponseEntity<String> {
        val result = storageService.data;
        return ResponseEntity.ok(result.toString());
    }
}
