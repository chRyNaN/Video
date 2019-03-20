package com.chrynan.common.model

data class Publisher(
    val id: ID,
    val created: String,
    val lastUpdated: String,
    val name: String,
    val description: String,
    val details: String? = null,
    val website: Uri? = null,
    val imageUri: Uri? = null
)