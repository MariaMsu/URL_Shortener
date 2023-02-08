package com.example.url_shortener.service

import com.example.url_shortener.dto.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * This service is a wrapper around one of the three roles (Leader, Follower or Candidate)
 * in the Raft protocol.
 */
@Service
class RaftService : RaftProtocolHandler {

    @Autowired
    lateinit var storageService: StorageService

    @Autowired
    lateinit var communicator: OutboundRaftCommunicationService;

    private val logger = LoggerFactory.getLogger(javaClass)

    var role: RaftRole = RaftRole.FOLLOWER

    init {
        // TODO remove it
        val strEnvVar: String = System.getenv("LEAD") ?: "0"
        val envVar: Int = strEnvVar.toInt()
        if (envVar != 0) {
            role = RaftRole.LEADER
        }
        logger.info("ROLE is $role")
    }

    @Synchronized
    override fun saveLongUrl(longUrl: String): String {
        if (role == RaftRole.LEADER) {
            val appendEntryArgs = storageService.leaderLogUrlAndComputeAEDto(longUrl = longUrl)
            for (port in TmpInfo().followerPorts) {
                val address = "127.0.0.1:$port"
                communicator.appendEntries(destination = address, entries = appendEntryArgs)
            }
            return appendEntryArgs.entry.shortUrl
        } else {
            TODO("not implemented, redirect to leader")
        }
    }


    @Synchronized
    override fun appendEntries(appendEntryArgs: AppendEntryArgsDto): Boolean {
        // theoretically, this function should not be called if role=Leader
        // but even if the leader called it, nothing should break
        val result = storageService.logEntry(appendEntryArgs)
        logger.info("$role updated log:\n${storageService.entryLog}\n")
        return result
    }

    @Synchronized
    override fun requestVote(requestVote: RequestVoteArgsDto): RequestVoteReplyDto {
        TODO("Maria: not implemented")
    }

    @Synchronized
    override fun appendEntriesReply(appendEntryReply: AppendEntryReplyDto, from: String) {
        TODO("Maria: not implemented")
    }

    @Synchronized
    override fun requestVoteReply(requestVoteReply: RequestVoteReplyDto) {
        TODO("Maria: not implemented")
    }
}