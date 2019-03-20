package com.chrynan.common.model

data class ChannelEdge(
    override val cursor: Cursor,
    override val node: Channel
) : Edge<Channel>