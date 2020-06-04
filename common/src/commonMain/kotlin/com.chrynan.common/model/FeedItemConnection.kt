package com.chrynan.common.model

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo

data class FeedItemConnection(
    override val totalCount: Int = 0,
    override val pageInfo: PageInfo = PageInfo(),
    override val edges: List<FeedItemEdge> = emptyList(),
    override val nodes: List<FeedItem> = emptyList()
) : Connection<FeedItem, FeedItemEdge>