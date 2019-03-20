package com.chrynan.common.model

data class PublisherConnection(
    override val totalCount: Int,
    override val pageInfo: PageInfo,
    override val edges: List<PublisherEdge>
) : Connection<Publisher, PublisherEdge>