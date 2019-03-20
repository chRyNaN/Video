package com.chrynan.common.model

data class VideoContributorEdge(
    override val cursor: Cursor,
    override val node: VideoContributor
) : Edge<VideoContributor>