package com.chrynan.video.model

data class VideoPlayerState(
    val isAutoPlayEnabled: Boolean = true,
    val startPositionInMilliseconds: Long = 0L,
    val isOptionsVisible: Boolean = false
)