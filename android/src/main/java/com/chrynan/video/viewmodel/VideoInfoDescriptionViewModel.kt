package com.chrynan.video.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.VideoInfo

data class VideoInfoDescriptionViewModel(
    val videoInfo: VideoInfo,
    val description: String
) : AdapterItem {

    override val uniqueAdapterId: AdapterId = "VideoDescription:${videoInfo}".asUniqueAdapterId()
}