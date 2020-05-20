package com.chrynan.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.presentation.model.ResourceID

data class VideoInfoActionViewModel(
    val videoInfo: VideoInfo,
    val text: String,
    val icon: ResourceID,
    val isSelected: Boolean,
    val type: ActionType
) : AdapterItem {

    override val uniqueAdapterId = "VideoAction:$videoInfo".asUniqueAdapterId()

    enum class ActionType {

        LIKE,
        DISLIKE,
        SHARE
    }
}