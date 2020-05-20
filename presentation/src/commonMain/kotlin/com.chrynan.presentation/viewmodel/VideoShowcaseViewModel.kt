package com.chrynan.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

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