package com.chrynan.common.model

data class User(
    val id: ID,
    val name: String,
    val email: String,
    val created: String,
    val lastUpdated: String,
    val username: String? = null,
    val phoneNumber: String? = null
)