package com.chrynan.common.model.api

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Node
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class FeedItem : Node {

    @SerialName(value = "type")
    abstract val type: String

    @Serializable
    data class VideoFeedItem(
        @SerialName(value = "channel") val channel: Channel,
        @SerialName(value = "video") val video: Video
    ) : FeedItem() {

        override val id: ID = video.id

        override val type = "VideoFeedItem"
    }
}