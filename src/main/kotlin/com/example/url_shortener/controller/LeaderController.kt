package com.example.url_shortener.controller

import com.example.url_shortener.service.ReplicationService
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class LeaderController {
    var replicationService = ReplicationService();

    val nodeAddresses = arrayOf("http://127.0.0.1:6001/node-set-long-url")  // TODO
    @GetMapping("/set-long-url")
    fun setLongUrl(@RequestParam("url") longUrl: String): ResponseEntity<String> {
        val result = runBlocking{ replicationService.replicateAcrossAllNodes(longUrl, nodeAddresses);}
        return ResponseEntity.ok(result);
    }
}
