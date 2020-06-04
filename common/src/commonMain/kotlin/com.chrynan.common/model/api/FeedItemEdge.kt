package com.chrynan.common.model.api

import com.chrynan.common.model.api.FeedItem
import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.core.Edge

data class FeedItemEdge(
    override val cursor: Cursor,
    override val node: FeedItem
) : Edge<FeedItem>