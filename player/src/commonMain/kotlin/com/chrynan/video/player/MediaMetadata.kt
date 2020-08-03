package com.chrynan.video.player

data class MediaMetadata(
    val uri: String,
    val title: String? = null,
    val author: String? = null,
    val date: String? = null,
    val duration: Long? = null,
    val mimeType: String? = null,
    val frameCount: Int? = null,
    val height: Int? = null,
    val width: Int? = null,
    val rotation: Float? = null
)