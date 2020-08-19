package com.chrynan.video.common.model.api

data class FeedItem<N : FeedQuery.NodeFeedItem>(
    val provider: FeedQuery.Provider,
    val item: N
)