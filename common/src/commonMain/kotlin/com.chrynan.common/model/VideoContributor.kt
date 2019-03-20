package com.chrynan.common.model

data class VideoContributor(
    val id: ID,
    val name: String,
    val position: String,
    val role: VideoCreditRole,
    val imageUri: Uri? = null
)