package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.VideoInfo

data class VideoRecommendationViewModel(
    val title: String,
    val channelName: String,
    val detailText: String,
    val videoInfo: VideoInfo,
    val videoLength: String
) : AdapterItem {

    override val uniqueAdapterId =
        videoInfo.run { "Recommendation:$providerUri:$channelId:$videoId".asUniqueAdapterId() }
}