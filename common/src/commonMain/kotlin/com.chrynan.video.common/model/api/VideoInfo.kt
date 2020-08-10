package com.chrynan.video.common.model.api

import com.chrynan.video.common.model.core.ID
import com.chrynan.video.common.model.core.UriString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoInfo(
    @SerialName(value = "videoId") val videoId: ID,
    @SerialName(value = "channelId") val channelId: ID,
    @SerialName(value = "providerUri") val providerUri: UriString,
    @SerialName(value = "videoUri") val videoUri: UriString,
    @SerialName(value = "previewImageUri") val previewImageUri: UriString? = null
)