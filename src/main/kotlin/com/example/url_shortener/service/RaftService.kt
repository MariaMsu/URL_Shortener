package com.example.url_shortener.service

import com.example.url_shortener.dto.AppendEntryArgsDto
import com.example.url_shortener.dto.AppendEntryReplyDto
import com.example.url_shortener.dto.RequestVoteArgsDto
import com.example.url_shortener.dto.RequestVoteReplyDto
import org.springframework.stereotype.Service

/**
 * This service is a wrapper around one of the three roles (Leader, Follower or Candidate)
 * in the Raft protocol.
 */
@Service
class RaftService : RaftProtocolHandler {
    var role: RaftRole = RaftRole.FOLLOWER

    @Synchronized
    override fun appendEntries(appendEntryArgs: AppendEntryArgsDto) = role.appendEntries(appendEntryArgs)

    @Synchronized
    override fun requestVote(requestVote: RequestVoteArgsDto) = role.requestVote(requestVote)

    @Synchronized
    override fun appendEntriesReply(appendEntryReply: AppendEntryReplyDto, from: String) =
        role.appendEntriesReply(appendEntryReply, from)

    @Synchronized
    override fun requestVoteReply(requestVoteReply: RequestVoteReplyDto) = role.requestVoteReply(requestVoteReply)
}