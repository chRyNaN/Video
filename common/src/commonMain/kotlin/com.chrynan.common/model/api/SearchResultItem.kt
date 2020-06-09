package com.chrynan.common.model.api

import com.chrynan.common.model.core.Node
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class SearchResultItem : Node {

    abstract val type: String

    @Serializable
    data class VideoSearchResultItem(
        @SerialName(value = "video") val video: Video,
        @SerialName(value = "channel") val channel: Channel,
        @SerialName(value = "__typename") override val type: String
    ) : SearchResultItem() {

        override val id = video.id
    }

    @Serializable
    data class ChannelSearchResultItem(
        @SerialName(value = "channel") val channel: Channel,
        @SerialName(value = "__typename") override val type: String
    ) : SearchResultItem() {

        override val id = channel.id
    }
}