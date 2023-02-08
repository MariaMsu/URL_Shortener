package com.example.url_shortener.dto

data class AppendEntryArgsDto(
    val term: Int,
    val leaderId: Int,
//    val entries: List<Entry>,  // skip this optimization
    val entry: Entry,
    val previousLogIndex: Int,  // ASK. Why do we need previousLogIndex, if we have a logIndex in the entry's field
    val previousLogTerm: Int,
    val leaderCommit: Int
)

