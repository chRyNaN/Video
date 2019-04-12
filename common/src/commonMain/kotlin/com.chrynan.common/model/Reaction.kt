package com.chrynan.common.model

data class Reaction(
    val id: ID,
    val created: String,
    val lastUpdated: String,
    val video: Video,
    val user: User,
    val reaction: String,
    val type: String? = null
)