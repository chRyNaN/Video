package com.chrynan.common.model.result

import com.chrynan.common.model.api.VideoResult

sealed class FeedResultItem {

    data class Video(
        val videoResult: VideoResult
    ) : FeedResultItem()
}