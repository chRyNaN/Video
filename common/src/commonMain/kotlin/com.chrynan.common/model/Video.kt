package com.chrynan.common.model

data class Video(
    val id: ID,
    val created: String,
    val published: String,
    val lastUpdated: String,
    val name: String,
    val description: String,
    val details: String? = null,
    val imageUri: Uri? = null,
    val category: String? = null,
    val tags: List<String> = emptyList(),
    val viewCount: Long,
    val supportsRating: Boolean,
    val videoDetails: VideoDetails,
    val contentRating: ContentRating
)