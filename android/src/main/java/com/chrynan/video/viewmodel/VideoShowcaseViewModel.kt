package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.VideoInfo

data class VideoShowcaseViewModel(
    val videoInfo: VideoInfo,
    val title: String,
    val details: String,
    val provider: String,
    val videoLength: String,
    val videoImageUrl: String,
    val channelImageUrl: String
) : AdapterItem {

    override val uniqueAdapterId =
        videoInfo.run { "Showcase:$providerUri:$channelId:$videoId".asUniqueAdapterId() }
}