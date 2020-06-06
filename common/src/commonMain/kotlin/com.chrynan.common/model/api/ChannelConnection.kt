package com.chrynan.common.model.api

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelConnection(
    @SerialName(value = "totalCount") override val totalCount: Int = 0,
    @SerialName(value = "pageInfo") override val pageInfo: PageInfo = PageInfo(),
    @SerialName(value = "edges") override val edges: List<ChannelEdge> = emptyList(),
    @SerialName(value = "nodes") override val nodes: List<Channel> = emptyList()
) : Connection<Channel, ChannelEdge>