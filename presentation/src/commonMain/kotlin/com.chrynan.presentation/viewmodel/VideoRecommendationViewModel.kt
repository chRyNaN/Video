package com.chrynan.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

data class VideoRecommendationViewModel(
    val title: String,
    val channelName: String,
    val detailText: String,
    val videoInfo: VideoInfo,
    val videoImageUrl: String,
    val videoLength: String
) : AdapterItem {

    override val uniqueAdapterId =
        videoInfo.run { "Recommendation:$providerUri:$channelId:$videoId".asUniqueAdapterId() }
}