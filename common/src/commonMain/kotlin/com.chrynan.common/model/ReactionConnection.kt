package com.chrynan.common.model

data class ReactionConnection(
    override val pageInfo: PageInfo,
    override val totalCount: Int,
    override val edges: List<ReactionEdge>
) : Connection<Reaction, ReactionEdge>