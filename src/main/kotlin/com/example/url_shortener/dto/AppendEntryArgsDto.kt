package com.example.url_shortener.dto

data class AppendEntryArgsDto(
    val term: Int,
    val leaderId: Int,
//    val entries: List<Entry>,  // skip this optimization
    val entry: Entry,
    val prevLogIndex: Int,
    val prevLogTerm: Int,
    val leaderCommit: Int
)

