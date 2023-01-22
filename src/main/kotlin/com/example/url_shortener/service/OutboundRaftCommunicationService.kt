package com.example.url_shortener.service

import com.example.url_shortener.dto.AppendEntryArgsDto
import com.example.url_shortener.dto.AppendEntryReplyDto
import com.example.url_shortener.dto.RequestVoteArgsDto
import com.example.url_shortener.dto.RequestVoteReplyDto
import org.springframework.core.task.TaskExecutor
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

/**
 * This service handles REST calls to the other nodes of our system.
 */
@Service
class OutboundRaftCommunicationService(val taskExecutor: TaskExecutor) {
    val HEARTBEAT_TIMEOUT: Long = 2000

    fun appendEntries(destination: String, entries: AppendEntryArgsDto): AppendEntryReplyDto {
        return sendPost(destination, "/append-entries", entries, AppendEntryReplyDto::class.java)
    }

    fun requestVote(destination: String, message: RequestVoteArgsDto): RequestVoteReplyDto {
        return sendPost(destination, "/request-vote", message, RequestVoteReplyDto::class.java)
    }

    private fun <T, U> sendPost(destination: String, route: String, message: T, replyClass: Class<U>): U {
        return CompletableFuture.supplyAsync(fun(): U {
            val endpoint = "http://$destination$route";
            val template = RestTemplate();

            //TODO: The nullable version seems kinda sketchy
            return template.postForEntity(endpoint, message, replyClass).body!!
        }, taskExecutor)
            .get(HEARTBEAT_TIMEOUT, TimeUnit.MILLISECONDS)
    }
}