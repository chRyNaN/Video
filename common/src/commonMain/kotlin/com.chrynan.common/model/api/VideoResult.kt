package com.chrynan.common.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResult(
    @SerialName(value = "info") val info: VideoInfo,
    @SerialName(value = "video") val video: Video,
    @SerialName(value = "channel") val channel: Channel,
    @SerialName(value = "provider") val provider: Provider
)