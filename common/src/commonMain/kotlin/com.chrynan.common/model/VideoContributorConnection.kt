package com.chrynan.common.model

data class VideoContributorConnection(
    override val totalCount: Int,
    override val pageInfo: PageInfo,
    override val edges: List<VideoContributorEdge>
) : Connection<VideoContributor, VideoContributorEdge>