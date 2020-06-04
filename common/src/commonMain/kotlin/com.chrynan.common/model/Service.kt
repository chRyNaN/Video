package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

data class Service(
    val providerUri: UriString,
    val apiVersion: String,
    val token: String? = null
)