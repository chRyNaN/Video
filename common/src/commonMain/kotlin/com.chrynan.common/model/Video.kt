package com.chrynan.common.model

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Node
import com.chrynan.common.model.core.UriString

data class Video(
    override val id: ID,
    val created: String,
    val published: String,
    val lastUpdated: String,
    val name: String,
    val description: String,
    val details: String? = null,
    val imageUri: UriString? = null,
    val category: String? = null,
    val tags: List<String> = emptyList(),
    val viewCount: Long,
    val supportsRating: Boolean,
    val videoDetails: VideoDetails,
    val contentRating: ContentRating
) : Node