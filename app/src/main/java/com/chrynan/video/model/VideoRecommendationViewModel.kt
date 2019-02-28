package com.chrynan.video.model

import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.aaaah.asUniqueAdapterId

data class VideoRecommendationViewModel(
    val title: String,
    val channelName: String,
    val detailText: String,
    val videoId: String,
    val channelId: String,
    val providerUrl: String,
    val videoImageUrl: String,
    val videoLength: String
) : UniqueAdapterItem {

    override val uniqueAdapterId = "Recommendation:$providerUrl:$channelId:$videoId".asUniqueAdapterId()
}