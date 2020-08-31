package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.api.VideoInfo
import com.chrynan.video.presentation.core.AdapterItem
import com.chrynan.video.presentation.viewmodel.VideoInfoActionViewModel

data class VideoInfoHeaderViewModel(
    val videoInfo: VideoInfo,
    val title: String,
    val detail: String,
    val actions: List<VideoInfoActionViewModel> = emptyList(),
    val provider: VideoInfoProviderViewModel
) : AdapterItem {

    override val uniqueAdapterId = "HeaderInfo:$videoInfo".asUniqueAdapterId()
}