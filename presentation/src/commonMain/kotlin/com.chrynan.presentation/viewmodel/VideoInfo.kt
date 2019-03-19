package com.chrynan.presentation.viewmodel

import com.chrynan.common.model.Uri

data class VideoInfo(
    val videoId: String,
    val channelId: String,
    val providerUri: Uri,
    val videoUri: Uri
)