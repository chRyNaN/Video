package com.chrynan.video.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.api.VideoInfo

data class VideoInfoAboutViewModel(
    val videoInfo: VideoInfo,
    val about: String?
) : AdapterItem {

    override val uniqueAdapterId: AdapterId = "VideoDescription:${videoInfo}".asUniqueAdapterId()
}