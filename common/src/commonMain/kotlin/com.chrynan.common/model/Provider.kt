package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

data class Provider(
    val uri: UriString,
    val created: String,
    val lastUpdated: String,
    val name: String,
    val description: String? = null,
    val about: String? = null,
    val website: UriString? = null,
    val contactEmail: String? = null,
    val imageUri: UriString? = null
)