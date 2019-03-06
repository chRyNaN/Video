package com.chrynan.video.model

import com.chrynan.video.utils.ChannelId
import com.chrynan.video.utils.ProviderUri
import com.chrynan.video.utils.VideoId
import com.chrynan.video.utils.VideoUri

data class VideoInfo(
    val videoId: VideoId,
    val channelId: ChannelId,
    val providerUri: ProviderUri,
    val videoUri: VideoUri
)