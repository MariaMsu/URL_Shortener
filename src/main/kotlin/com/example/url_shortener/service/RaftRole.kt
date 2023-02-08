package com.example.url_shortener.service

/**
 * In the raft protocol, there are three roles: Leader, Follower and Candidate.
 */
enum class RaftRole{
    LEADER, FOLLOWER, CANDIDATE
}

//enum class RaftRole: RaftProtocolHandler {
//    LEADER {
//        override fun appendEntries(appendEntryArgs: AppendEntryArgsDto): AppendEntryReplyDto {
//            TODO("Not yet implemented")
//        }
//
//        override fun appendEntriesReply(appendEntryReply: AppendEntryReplyDto, from: String) {
//            TODO("Not yet implemented")
//        }
//
//        override fun requestVote(requestVote: RequestVoteArgsDto): RequestVoteReplyDto {
//            TODO("Not yet implemented")
//        }
//
//        override fun requestVoteReply(requestVoteReply: RequestVoteReplyDto) {
//            TODO("Not yet implemented")
//        }
//    },
//
//    FOLLOWER {
//        override fun appendEntries(appendEntryArgs: AppendEntryArgsDto): AppendEntryReplyDto {
//            TODO("Not yet implemented")
//        }
//
//        override fun appendEntriesReply(appendEntryReply: AppendEntryReplyDto, from: String) {
//            TODO("Not yet implemented")
//        }
//
//        override fun requestVote(requestVote: RequestVoteArgsDto): RequestVoteReplyDto {
//            TODO("Not yet implemented")
//        }
//
//        override fun requestVoteReply(requestVoteReply: RequestVoteReplyDto) {
//            TODO("Not yet implemented")
//        }
//    },
//
//    CANDIDATE {
//        override fun appendEntries(appendEntryArgs: AppendEntryArgsDto): AppendEntryReplyDto {
//            TODO("Not yet implemented")
//        }
//
//        override fun appendEntriesReply(appendEntryReply: AppendEntryReplyDto, from: String) {
//            TODO("Not yet implemented")
//        }
//
//        override fun requestVote(requestVote: RequestVoteArgsDto): RequestVoteReplyDto {
//            TODO("Not yet implemented")
//        }
//
//        override fun requestVoteReply(requestVoteReply: RequestVoteReplyDto) {
//            TODO("Not yet implemented")
//        }
//    };
//}