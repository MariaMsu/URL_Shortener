package com.example.url_shortener.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ShortenerController {
    @GetMapping("/{id}")
    fun getHelloWorld(@PathVariable id: String): ResponseEntity<String> {
        return ResponseEntity.ok(id);
    }
}