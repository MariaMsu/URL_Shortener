package com.example.url_shortener.dto

data class RequestVoteArgsDto(
    val term: Int,
    val candidateId: Int,
    val lastLogIndex: Int,
    val lastLogTerm: Int
)

