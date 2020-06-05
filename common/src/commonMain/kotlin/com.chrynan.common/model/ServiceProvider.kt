package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

data class ServiceProvider(
    val providerUri: UriString,
    val apiVersion: String,
    val name: String,
    val token: String? = null,
    val imageUri: UriString? = null
)