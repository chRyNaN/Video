package com.chrynan.common.model

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo

data class ChannelConnection(
    override val totalCount: Int = 0,
    override val pageInfo: PageInfo = PageInfo(),
    override val edges: List<ChannelEdge> = emptyList(),
    override val nodes: List<Channel> = emptyList()
) : Connection<Channel, ChannelEdge>