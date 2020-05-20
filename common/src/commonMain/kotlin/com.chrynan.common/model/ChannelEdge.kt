package com.chrynan.common.model

import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.core.Edge

data class ChannelEdge(
    override val cursor: Cursor,
    override val node: Channel
) : Edge<Channel>