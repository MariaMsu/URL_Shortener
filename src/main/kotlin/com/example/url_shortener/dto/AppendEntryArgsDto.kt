package com.example.url_shortener.dto

data class AppendEntryArgsDto(
    val term: Int,
    val leaderId: Int,
    val entries: List<Entry>,
    val previousLogIndex: Int,
    val previousLogTerm: Int,
    val leaderCommit: Int
)

