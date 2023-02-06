package com.example.url_shortener.controller

import com.example.url_shortener.service.ReplicationService
import com.example.url_shortener.service.StorageService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class LeaderController {
    var replicationService = ReplicationService();

    @Autowired
    lateinit var storageService: StorageService;


    val nodeAddresses = arrayOf(
        "http://127.0.0.1:6001/set-data",
        "http://127.0.0.1:6002/set-data"
    )  // TODO

    @GetMapping("/set-long-url")
    fun setLongUrl(@RequestParam("url") longUrl: String): ResponseEntity<String> {
        val shortUrl = storageService.setUrl(longUrl);
        if (shortUrl == null){
            return ResponseEntity.ok("Not ok")  // todo message
        }
        val result = runBlocking{ replicationService.replicateAcrossAllNodes(longUrl, shortUrl, nodeAddresses);}
        return ResponseEntity.ok(result);
    }

    @GetMapping("/delete-long-url")
    fun deleteLongUrl(@RequestParam("url") longUrl: String): ResponseEntity<String> {
        return ResponseEntity.ok("TODO not implementer yet");
    }
}
