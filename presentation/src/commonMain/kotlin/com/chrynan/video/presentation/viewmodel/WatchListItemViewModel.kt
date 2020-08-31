package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.core.ID
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.presentation.core.AdapterItem

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