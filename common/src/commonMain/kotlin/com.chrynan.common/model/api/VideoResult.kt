package com.chrynan.common.model.api

import com.chrynan.common.model.api.Channel
import com.chrynan.common.model.api.Provider
import com.chrynan.common.model.api.Video
import com.chrynan.common.model.api.VideoInfo

data class VideoResult(
    val info: VideoInfo,
    val video: Video,
    val channel: Channel,
    val provider: Provider
)