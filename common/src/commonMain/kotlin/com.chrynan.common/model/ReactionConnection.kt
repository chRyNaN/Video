package com.chrynan.common.model

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo

data class ReactionConnection(
    override val totalCount: Int = 0,
    override val pageInfo: PageInfo = PageInfo(),
    override val edges: List<ReactionEdge> = emptyList(),
    override val nodes: List<Reaction> = emptyList()
) : Connection<Reaction, ReactionEdge>