package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString

data class WatchListItemViewModel(
    val providerUri: UriString,
    val videoId: ID,
    val videoImageUri: String,
    val title: String,
    val description: String,
    val secondaryDescription: String
) : AdapterItem {

    override val uniqueAdapterId =
        "Watch List Item: providerUri = $providerUri; videoId = $videoId".asUniqueAdapterId()
}