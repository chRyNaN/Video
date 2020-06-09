package com.chrynan.common.model.wrapper

import com.chrynan.common.model.api.VideoResult

sealed class FeedItemWrapper {

    data class Video(
        val videoResult: VideoResult
    ) : FeedItemWrapper()
}