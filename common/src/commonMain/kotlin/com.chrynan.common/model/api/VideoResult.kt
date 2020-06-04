package com.chrynan.common.model.api

data class VideoResult(
    val info: VideoInfo,
    val video: Video,
    val channel: Channel,
    val provider: Provider
)