package com.example.url_shortener.dto

data class AppendEntryReplyDto(
    val term: Int,
    val success: Boolean,
    val conflictIndex: Int,
    val conflictTerm: Int
)