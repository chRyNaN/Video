package com.chrynan.common.model

data class Provider(
    val uri: Uri,
    val created: String,
    val lastUpdated: String,
    val name: String,
    val description: String,
    val details: String? = null,
    val website: Uri? = null,
    val contactEmail: String,
    val contactPhoneNumber: String,
    val imageUri: Uri? = null
)