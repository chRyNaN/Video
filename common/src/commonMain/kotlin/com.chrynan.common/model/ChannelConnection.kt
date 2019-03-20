package com.chrynan.common.model

data class ChannelConnection(
    override val totalCount: Int,
    override val pageInfo: PageInfo,
    override val edges: List<ChannelEdge>
) : Connection<Channel, ChannelEdge>