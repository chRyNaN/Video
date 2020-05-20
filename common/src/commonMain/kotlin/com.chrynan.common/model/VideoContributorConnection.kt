package com.chrynan.common.model

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo

data class VideoContributorConnection(
    override val totalCount: Int = 0,
    override val pageInfo: PageInfo = PageInfo(),
    override val edges: List<VideoContributorEdge> = emptyList(),
    override val nodes: List<VideoContributor> = emptyList()
) : Connection<VideoContributor, VideoContributorEdge>