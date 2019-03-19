package com.chrynan.common.model

data class Uri(
    val scheme: String,
    val authority: String? = null,
    val userInfo: String? = null,
    val host: String? = null,
    val port: String? = null,
    val path: String,
    val query: String? = null,
    val fragment: String? = null
)