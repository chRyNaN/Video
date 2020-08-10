package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.api.VideoInfo

data class VideoRecommendationViewModel(
    val title: String,
    val channelName: String,
    val detailText: String? = null,
    val videoInfo: VideoInfo,
    val videoLength: String
) : AdapterItem {

    override val uniqueAdapterId =
        videoInfo.run { "Recommendation:$providerUri:$channelId:$videoId".asUniqueAdapterId() }
}