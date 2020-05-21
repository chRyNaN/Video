package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.VideoInfo

data class VideoInfoHeaderViewModel(
    val videoInfo: VideoInfo,
    val title: String,
    val detail: String,
    val actions: List<VideoInfoActionViewModel> = emptyList(),
    val provider: VideoInfoProviderViewModel
) : AdapterItem {

    override val uniqueAdapterId = "HeaderInfo:$videoInfo".asUniqueAdapterId()
}