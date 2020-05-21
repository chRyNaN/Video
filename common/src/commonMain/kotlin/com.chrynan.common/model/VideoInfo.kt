package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

data class VideoInfo(
    val videoId: String,
    val channelId: String,
    val providerUri: UriString,
    val videoUri: UriString
)