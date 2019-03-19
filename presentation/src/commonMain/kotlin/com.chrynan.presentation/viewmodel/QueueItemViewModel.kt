package com.chrynan.presentation.viewmodel

import com.chrynan.common.model.Uri

data class QueueItemViewModel(
    val videoInfo: VideoInfo,
    val videoImageUri: Uri,
    val title: String,
    val isActivated: Boolean,
    val position: Int
) : UniqueListItem {

    override val uniqueListId =
        "Queue Item: videoInfo = $videoInfo; position = $position; isActivated = $isActivated".asUniqueListId()
}