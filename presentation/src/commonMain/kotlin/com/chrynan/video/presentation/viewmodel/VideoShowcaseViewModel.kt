package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.core.ID
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.presentation.core.AdapterItem

data class VideoShowcaseViewModel(
    val providerUri: UriString,
    val videoId: ID,
    val title: String,
    val details: String,
    val previewImage: UriString? = null,
    val provider: String,
    val videoLength: String,
    val channelImageUrl: UriString? = null
) : AdapterItem {

    override val uniqueAdapterId = "Showcase:$providerUri:$videoId".asUniqueAdapterId()
}