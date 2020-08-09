package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.model.core.UriString

data class VideoShowcaseViewModel(
    val videoInfo: VideoInfo,
    val title: String,
    val details: String,
    val provider: String,
    val videoLength: String,
    val channelImageUrl: UriString? = null
) : AdapterItem {

    override val uniqueAdapterId =
        videoInfo.run { "Showcase:$providerUri:$channelId:$videoId".asUniqueAdapterId() }
}