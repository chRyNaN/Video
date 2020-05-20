package com.chrynan.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

data class VideoInfoHeaderViewModel(
    val videoInfo: VideoInfo,
    val title: String,
    val viewCount: String,
    val supportsRating: Boolean = false,
    val likeButtonText: String,
    val dislikeButtonText: String,
    val shareButtonText: String,
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false
) : AdapterItem {

    override val uniqueAdapterId = "HeaderInfo:$videoInfo".asUniqueAdapterId()
}