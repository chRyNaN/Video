package com.chrynan.presentation.viewmodel

data class QueueItemViewModel(
    val videoInfo: VideoInfo,
    val videoImageUri: String,
    val title: String,
    val isActivated: Boolean,
    val position: Int
) : UniqueListItem {

    override val uniqueListId =
        "Queue Item: videoInfo = $videoInfo; position = $position; isActivated = $isActivated".asUniqueListId()
}