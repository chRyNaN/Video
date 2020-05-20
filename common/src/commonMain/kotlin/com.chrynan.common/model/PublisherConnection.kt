package com.chrynan.common.model

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo

data class PublisherConnection(
    override val totalCount: Int = 0,
    override val pageInfo: PageInfo = PageInfo(),
    override val edges: List<PublisherEdge> = emptyList(),
    override val nodes: List<Publisher> = emptyList()
) : Connection<Publisher, PublisherEdge>