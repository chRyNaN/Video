package com.chrynan.presentation.viewmodel

import com.chrynan.common.model.Uri

data class WatchListItemViewModel(
    val videoInfo: VideoInfo,
    val videoImageUri: Uri,
    val title: String,
    val description: String,
    val secondaryDescription: String
) : UniqueListItem {

    override val uniqueListId = "Watch List Item: videoInfo = $videoInfo".asUniqueListId()
}