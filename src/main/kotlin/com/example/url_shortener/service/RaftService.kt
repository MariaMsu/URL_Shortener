package com.example.url_shortener.service

import com.example.url_shortener.dto.AppendEntryArgsDto
import com.example.url_shortener.dto.AppendEntryReplyDto
import com.example.url_shortener.dto.RequestVoteArgsDto
import com.example.url_shortener.dto.RequestVoteReplyDto
import org.springframework.stereotype.Service

@Service
class RaftService {
    //TODO: Schedule

    fun timerExpired() {
        // Call for a new election
    }

    fun heartbeat(args: AppendEntryArgsDto): AppendEntryReplyDto? {
        return null
    }

    fun callForElection(args: RequestVoteArgsDto): RequestVoteReplyDto? {
        return null
    }
}