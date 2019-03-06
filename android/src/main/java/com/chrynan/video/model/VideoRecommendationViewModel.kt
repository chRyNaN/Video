package com.chrynan.video.model

import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.aaaah.asUniqueAdapterId

data class VideoRecommendationViewModel(
    val title: String,
    val channelName: String,
    val detailText: String,
    val videoInfo: VideoInfo,
    val videoImageUrl: String,
    val videoLength: String
) : UniqueAdapterItem {

    override val uniqueAdapterId =
        videoInfo.run { "Recommendation:$providerUri:$channelId:$videoId".asUniqueAdapterId() }
}