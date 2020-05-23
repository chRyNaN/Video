package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.VideoInfo
import com.chrynan.common.model.core.UriString

data class VideoShowcaseViewModel(
    val videoInfo: VideoInfo,
    val title: String,
    val details: String,
    val provider: String,
    val videoLength: String,
    val channelImageUrl: UriString
) : AdapterItem {

    override val uniqueAdapterId =
        videoInfo.run { "Showcase:$providerUri:$channelId:$videoId".asUniqueAdapterId() }
}