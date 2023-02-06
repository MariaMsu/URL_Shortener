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
    fun appendEntries(@RequestBody args: AppendEntryArgsDto): ResponseEntity<AppendEntryReplyDto> {
        return ResponseEntity.ok(this.service.appendEntries(args))
    }

    /**
     *
     */
    @PostMapping("/request-vote")
    fun requestVote(@RequestBody args: RequestVoteArgsDto): ResponseEntity<RequestVoteReplyDto> {
        return ResponseEntity.ok(service.requestVote(args))
    }
}