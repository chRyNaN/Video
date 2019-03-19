package com.chrynan.presentation.viewmodel

data class VideoInfoViewModel(
    val videoInfo: VideoInfo,
    val title: String,
    val viewCount: String,
    val description: String,
    val publishedDate: String,
    val category: String,
    val showCategory: Boolean = false,
    val tags: List<String> = emptyList(),
    val showTags: Boolean = false,
    val supportsRating: Boolean = false,
    val likeButtonText: String,
    val dislikeButtonText: String,
    val shareButtonText: String,
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false,
    val providerServiceName: String,
    val channelName: String,
    val channelImageUrl: String,
    val channelSubscriberCount: String,
    val showChannelSubscribeCount: Boolean = false,
    val isSubscribedToChannel: Boolean = false
) : UniqueListItem {

    override val uniqueListId = videoInfo.run { "Info:$providerUri:$channelId:$videoId".asUniqueListId() }
}