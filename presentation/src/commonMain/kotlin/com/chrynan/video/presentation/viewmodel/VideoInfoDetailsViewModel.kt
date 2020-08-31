package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.api.VideoInfo
import com.chrynan.video.presentation.core.AdapterItem

data class VideoInfoDetailsViewModel(
    val videoInfo: VideoInfo,
    val category: String? = null,
    val tags: List<String> = emptyList()
) : AdapterItem {

    override val uniqueAdapterId: AdapterId = "VideoDetails:$videoInfo".asUniqueAdapterId()
}