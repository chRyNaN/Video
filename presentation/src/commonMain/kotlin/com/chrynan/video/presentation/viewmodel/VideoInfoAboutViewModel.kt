package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.api.VideoInfo

data class VideoInfoAboutViewModel(
    val videoInfo: VideoInfo,
    val about: String?
) : AdapterItem {

    override val uniqueAdapterId: AdapterId = "VideoDescription:${videoInfo}".asUniqueAdapterId()
}