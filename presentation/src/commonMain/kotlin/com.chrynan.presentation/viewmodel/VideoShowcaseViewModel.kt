package com.chrynan.presentation.viewmodel

data class VideoShowcaseViewModel(
    val videoInfo: VideoInfo,
    val title: String,
    val details: String,
    val provider: String,
    val videoLength: String,
    val videoImageUrl: String,
    val channelImageUrl: String
) : UniqueListItem {

    override val uniqueListId = videoInfo.run { "Showcase:$providerUri:$channelId:$videoId".asUniqueListId() }
}