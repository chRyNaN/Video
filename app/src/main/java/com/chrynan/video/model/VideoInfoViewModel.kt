package com.chrynan.video.model

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.UniqueAdapterItem

data class VideoInfoViewModel(
    override val uniqueAdapterId: AdapterId,
    val title: String,
    val viewCount: String,
    val description: String,
    val publishedDate: String,
    val category: String? = null,
    val tags: List<String> = emptyList(),
    val supportsRating: Boolean = false,
    val likeCount: String? = null,
    val dislikeCount: String? = null,
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false,
    val channelName: String,
    val channelImageUrl: String,
    val channelSubscriberCount: String? = null,
    val isSubscribedToChannel: Boolean = false
) : UniqueAdapterItem