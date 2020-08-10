package com.chrynan.video.common.model.api

data class VideoDetails(
    val videoInfo: VideoInfo,
    val data: VideoDetailsQuery.Data
)