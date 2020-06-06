package com.chrynan.common.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class VideoStreamType {

    @SerialName(value = "PROGRESSIVE")
    PROGRESSIVE,

    @SerialName(value = "DASH")
    DASH,

    @SerialName(value = "SMOOTH_STREAMING")
    SMOOTH_STREAMING,

    @SerialName(value = "HLS")
    HLS
}