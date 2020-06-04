package com.chrynan.common.model.api

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo

data class ChannelVideoListConnection(
    val name: String,
    override val totalCount: Int = 0,
    override val pageInfo: PageInfo = PageInfo(),
    override val edges: List<VideoEdge> = emptyList(),
    override val nodes: List<Video> = emptyList()
) : Connection<Video, VideoEdge>