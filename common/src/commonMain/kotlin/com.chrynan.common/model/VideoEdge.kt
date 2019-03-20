package com.chrynan.common.model

data class VideoEdge(
    override val cursor: Cursor,
    override val node: Video
) : Edge<Video>