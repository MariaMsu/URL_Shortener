package com.example.url_shortener.dto

data class AppendEntryArgsDto(
    val term: Int,
    val leaderId: Int,
    // TODO: Entries
    val previousLogIndex: Int,
    val previousLogTerm: Int,
    val leaderCommit: Int
)

