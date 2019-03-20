package com.chrynan.common.model

data class Note(
    val id: ID,
    val created: String,
    val lastUpdated: String,
    val content: String
)