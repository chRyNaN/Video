package com.chrynan.common.model

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo

data class VideoConnection(
    override val totalCount: Int = 0,
    override val pageInfo: PageInfo = PageInfo(),
    override val edges: List<VideoEdge> = emptyList(),
    override val nodes: List<Video> = emptyList()
) : Connection<Video, VideoEdge>