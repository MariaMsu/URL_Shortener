package com.example.url_shortener.controller

import com.example.url_shortener.service.StorageService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class NodeController {

    @Autowired
    lateinit var storageService: StorageService;

    private val logger = LoggerFactory.getLogger(javaClass)


    @GetMapping("/node-set-long-url/{longUrl}")
    fun setLongUrl(@PathVariable longUrl: String): ResponseEntity<String> {
        logger.info("setLongUrl: $longUrl")
        val result = storageService.setUrl(longUrl);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{shortUrl}")
    fun getShortUrl(@PathVariable shortUrl: String): ResponseEntity<String> {
        logger.info("getShortUrl: $shortUrl")
        val result = storageService.getUrl(shortUrl)
        return ResponseEntity.ok(result);
    }

    @GetMapping("/node-get-data")
    fun getData(): ResponseEntity<String> {
        logger.info("getData: ")
        val result = storageService.data;
        return ResponseEntity.ok(result.toString());
    }
}
