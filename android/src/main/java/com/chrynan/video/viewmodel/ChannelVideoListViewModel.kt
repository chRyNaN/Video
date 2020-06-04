package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.model.core.UriString

data class ChannelVideoListViewModel(
    val channelId: String,
    val providerUri: UriString,
    val listName: String,
    val items: List<ChannelVideoListItemViewModel> = emptyList()
) : AdapterItem {

    override val uniqueAdapterId = "ChannelVideoList:$channelId;$providerUri".asUniqueAdapterId()

    data class ChannelVideoListItemViewModel(
        val videoInfo: VideoInfo,
        val title: String,
        val description: String? = null
    ) : AdapterItem {

        override val uniqueAdapterId = "ChannelVideoListItem:$videoInfo".asUniqueAdapterId()
    }
}