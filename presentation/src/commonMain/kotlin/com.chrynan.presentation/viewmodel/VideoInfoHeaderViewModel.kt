package com.chrynan.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

data class VideoInfoHeaderViewModel(
    val videoInfo: VideoInfo,
    val title: String,
    val viewCount: String,
    val actions: List<VideoInfoActionViewModel> = emptyList()
) : AdapterItem {

    override val uniqueAdapterId = "HeaderInfo:$videoInfo".asUniqueAdapterId()
}