package com.chrynan.common.model

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Node

sealed class FeedItem : Node {

    abstract val type: String

    data class VideoFeedItem(
        val channel: Channel,
        val video: Video
    ) : FeedItem() {

        override val id: ID = video.id

        override val type = "VideoFeedItem"
    }
}