package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

data class Provider(
    val uri: UriString,
    val created: String,
    val lastUpdated: String,
    val name: String,
    val description: String,
    val details: String? = null,
    val website: UriString? = null,
    val contactEmail: String,
    val contactPhoneNumber: String,
    val imageUri: UriString? = null
)