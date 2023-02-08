package com.example.url_shortener.service

import com.example.url_shortener.dto.AppendEntryArgsDto
import com.example.url_shortener.dto.AppendEntryReplyDto
import com.example.url_shortener.dto.RequestVoteArgsDto
import com.example.url_shortener.dto.RequestVoteReplyDto

/**
 * A raft protocol handler knows how to deal with the messages defined by the protocol.
 */
interface RaftProtocolHandler {
    /**
     * This function is called whenever an inbound "append entries" message is received.
     * @param appendEntryArgs The message that was received.
     * @return The reply to the request.
     */
    fun appendEntries(appendEntryArgs: AppendEntryArgsDto): Boolean

    /**
     *
     */
    fun appendEntriesReply(appendEntryReply: AppendEntryReplyDto, from: String)

    /**
     * This function is called whenever an inbound "request vote" message is received.
     * @param requestVote The message that was received.
     * @return The reply to the request.
     */
    fun requestVote(requestVote: RequestVoteArgsDto): RequestVoteReplyDto

    /**
     *
     */
    fun requestVoteReply(requestVoteReply: RequestVoteReplyDto)
}