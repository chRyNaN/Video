package com.chrynan.presentation.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.asUniqueAdapterId

data class VideoInfoDetailsViewModel(
    val videoInfo: VideoInfo,
    val publishedDate: String,
    val category: String,
    val showCategory: Boolean = false,
    val tags: List<String> = emptyList(),
    val showTags: Boolean = false
) : AdapterItem {

    override val uniqueAdapterId: AdapterId = "VideoDetails:$videoInfo".asUniqueAdapterId()
}