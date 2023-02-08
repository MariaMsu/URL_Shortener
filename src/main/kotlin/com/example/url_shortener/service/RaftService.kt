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
    lateinit var communicator: OutboundRaftCommunicationService;

    private val logger = LoggerFactory.getLogger(javaClass)

    var role: RaftRole = RaftRole.FOLLOWER

    init {
        // TODO remove it
        val strEnvVar: String = System.getenv("LEAD") ?: "0"
        val envVar: Int = strEnvVar.toInt()
        if (envVar != 0){
            role = RaftRole.LEADER
        }
        logger.info("ROLE is $role")
    }


    // We initialize the first Entry to simplify processing
    val entryLog: MutableList<Entry> = mutableListOf(
        Entry(0, "", "")
    )

    @Synchronized
    override fun appendEntries(appendEntryArgs: AppendEntryArgsDto): Boolean {
        // TODO it is a draft
        if (role == RaftRole.FOLLOWER || role == RaftRole.CANDIDATE){
            val lastEntry = entryLog.last()
            val newEntry = appendEntryArgs.entry
            if (newEntry.logIndex == lastEntry.logIndex + 1){
                // todo other checks
                entryLog.add(newEntry)
                logger.info("$role updated log:\n$entryLog\n")
                return true
            }
            return false
        }

        if (role == RaftRole.LEADER){
            for (port in TmpInfo().followerPorts){
                val address = "127.0.0.1:$port"
                communicator.appendEntries(destination=address, entries = appendEntryArgs)
            }
            logger.info("LEADER: $role updated log:\n$entryLog\n")
            return true
        }
        TODO("Not implemented? Idk how to handle this warning")
    }

    @Synchronized
    override fun requestVote(requestVote: RequestVoteArgsDto): RequestVoteReplyDto {
        TODO("Maria: not implemented")
    }

    @Synchronized
    override fun appendEntriesReply(appendEntryReply: AppendEntryReplyDto, from: String){
        TODO("Maria: not implemented")
    }

    @Synchronized
    override fun requestVoteReply(requestVoteReply: RequestVoteReplyDto){
        TODO("Maria: not implemented")
    }
}