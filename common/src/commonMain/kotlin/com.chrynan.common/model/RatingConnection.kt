package com.chrynan.common.model

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo

data class RatingConnection(
    override val totalCount: Int = 0,
    override val pageInfo: PageInfo = PageInfo(),
    override val edges: List<RatingEdge> = emptyList(),
    override val nodes: List<Rating> = emptyList()
) : Connection<Rating, RatingEdge>