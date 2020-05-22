package com.chrynan.common.model

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString

data class VideoInfo(
    val videoId: ID,
    val channelId: ID,
    val providerUri: UriString,
    val videoUri: UriString,
    val previewImageUri: UriString? = null
)