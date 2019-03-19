package com.chrynan.presentation.viewmodel

data class VideoRecommendationViewModel(
    val title: String,
    val channelName: String,
    val detailText: String,
    val videoInfo: VideoInfo,
    val videoImageUrl: String,
    val videoLength: String
) : UniqueListItem {

    override val uniqueListId =
        videoInfo.run { "Recommendation:$providerUri:$channelId:$videoId".asUniqueListId() }
}