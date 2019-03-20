package com.chrynan.common.model

data class VideoConnection(
    override val totalCount: Int,
    override val pageInfo: PageInfo,
    override val edges: List<VideoEdge>
) : Connection<Video, VideoEdge>