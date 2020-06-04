package com.chrynan.common.model.api

import com.chrynan.common.model.core.*

data class Channel(
    override val id: ID,
    override val created: Moment,
    override val lastUpdated: Moment,
    val published: Moment,
    val name: String,
    val description: String? = null,
    val about: String? = null,
    val website: UriString? = null,
    val images: ChannelImageInfo = ChannelImageInfo(),
    val count: ChannelCountInfo = ChannelCountInfo(),
    val isSubscribed: Boolean = false,
    val lists: List<ChannelVideoListConnection> = emptyList(),
    val videos: VideoConnection = VideoConnection(),
    val video: Video? = null
) : Node,
    TimeDetail