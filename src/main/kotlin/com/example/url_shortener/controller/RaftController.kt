package com.example.url_shortener.controller

import com.example.url_shortener.dto.AppendEntryArgsDto
import com.example.url_shortener.dto.RequestVoteArgsDto
import com.example.url_shortener.dto.RequestVoteReplyDto
import com.example.url_shortener.service.RaftService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * The raft controller is responsible for inbound communication.
 *
 * @property service The raft service handles all internal raft protocol state.
 */
@Controller
class RaftController constructor(val service: RaftService) {
    /**
     *
     */
    @PostMapping("/append-entries")
    fun appendEntries(@RequestBody args: AppendEntryArgsDto): ResponseEntity<Boolean> {
        return ResponseEntity.ok(this.service.appendEntries(args))
    }

    /**
     *
     */
    @PostMapping("/request-vote")
    fun requestVote(@RequestBody args: RequestVoteArgsDto): ResponseEntity<RequestVoteReplyDto> {
        return ResponseEntity.ok(service.requestVote(args))
    }


    @PostMapping("/set-long-url")
    fun setLongUrl(@RequestBody longUrl: String): ResponseEntity<String> {
        return ResponseEntity.ok(this.service.setLongUrl(longUrl))
    }

    @GetMapping("/delete-long-url")
    fun deleteLongUrl(@RequestParam("url") longUrl: String): ResponseEntity<String> {
        return ResponseEntity.ok("TODO not implementer yet");
    }

    @GetMapping("/{shortUrl}")
    fun getShortUrl(@PathVariable shortUrl: String): ResponseEntity<String> {
        val result = this.service.getLongUrl(shortUrl)
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get-data")
    fun getData(): ResponseEntity<String> {
        val result = this.service.getAllData();
        return ResponseEntity.ok(result.toString());
    }
}