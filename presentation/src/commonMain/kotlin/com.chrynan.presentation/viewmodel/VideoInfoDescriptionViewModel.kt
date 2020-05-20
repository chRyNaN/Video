package com.chrynan.presentation.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.asUniqueAdapterId

data class VideoInfoDescriptionViewModel(
    val videoInfo: VideoInfo,
    val description: String
) : AdapterItem {

    override val uniqueAdapterId: AdapterId = "VideoDescription:${videoInfo}".asUniqueAdapterId()
}