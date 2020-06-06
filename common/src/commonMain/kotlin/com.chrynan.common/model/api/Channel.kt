package com.chrynan.common.model.api

import com.chrynan.common.model.core.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Channel(
    @SerialName(value = "id") override val id: ID,
    @SerialName(value = "created") override val created: Moment,
    @SerialName(value = "lastUpdated") override val lastUpdated: Moment,
    @SerialName(value = "published") val published: Moment,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "description") val description: String? = null,
    @SerialName(value = "about") val about: String? = null,
    @SerialName(value = "website") val website: UriString? = null,
    @SerialName(value = "images") val images: ChannelImageInfo = ChannelImageInfo(),
    @SerialName(value = "count") val count: ChannelCountInfo = ChannelCountInfo(),
    @SerialName(value = "isSubscribed") val isSubscribed: Boolean = false,
    @SerialName(value = "lists") val lists: List<ChannelVideoListConnection> = emptyList(),
    @SerialName(value = "videos") val videos: VideoConnection = VideoConnection(),
    @SerialName(value = "video") val video: Video? = null
) : Node,
    TimeDetail