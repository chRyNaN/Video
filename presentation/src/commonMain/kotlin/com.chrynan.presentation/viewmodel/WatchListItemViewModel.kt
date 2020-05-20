package com.chrynan.presentation.viewmodel

data class WatchListItemViewModel(
    val videoInfo: VideoInfo,
    val videoImageUri: String,
    val title: String,
    val description: String,
    val secondaryDescription: String
) : UniqueListItem {

    override val uniqueListId = "Watch List Item: videoInfo = $videoInfo".asUniqueListId()
}