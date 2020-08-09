package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString

data class ChannelListItemViewModel(
    val channelId: ID,
    val providerUri: UriString,
    val title: String,
    val description: String? = null,
    val channelImageUri: UriString? = null,
    val isSubscribed: Boolean = false
) : AdapterItem {

    override val uniqueAdapterId = "ChannelListItem:$channelId;$providerUri".asUniqueAdapterId()
}