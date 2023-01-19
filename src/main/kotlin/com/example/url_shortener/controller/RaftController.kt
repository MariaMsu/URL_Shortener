package com.example.url_shortener.controller

import com.example.url_shortener.dto.AppendEntryArgsDto
import com.example.url_shortener.dto.AppendEntryReplyDto
import com.example.url_shortener.dto.RequestVoteArgsDto
import com.example.url_shortener.dto.RequestVoteReplyDto
import com.example.url_shortener.service.RaftService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class RaftController public constructor(val service: RaftService) {
    @PostMapping("/heartbeat")
    fun heartbeat(@RequestBody args: AppendEntryArgsDto): ResponseEntity<AppendEntryReplyDto> {
        return ResponseEntity.ok(this.service.heartbeat(args))
    }

    @PostMapping("/election")
    fun callForElection(@RequestBody args: RequestVoteArgsDto): ResponseEntity<RequestVoteReplyDto> {
        return ResponseEntity.ok(service.callForElection(args))
    }
}