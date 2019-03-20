package com.chrynan.common.model

data class RatingConnection(
    override val totalCount: Int,
    override val pageInfo: PageInfo,
    override val edges: List<RatingEdge>
) : Connection<Rating, RatingEdge>