package com.chrynan.common.model.api

import com.chrynan.common.model.core.Node
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class FeedItem : Node {

    abstract val type: String

    @Serializable
    data class VideoFeedItem(
        @SerialName(value = "channel") val channel: Channel,
        @SerialName(value = "video") val video: Video,
        @SerialName(value = "__typename") override val type: String
    ) : FeedItem() {

        override val id = video.id
    }
}