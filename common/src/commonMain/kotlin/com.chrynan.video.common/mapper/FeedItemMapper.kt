package com.chrynan.video.common.mapper

import com.chrynan.video.common.Inject
import com.chrynan.video.common.model.api.FeedItem

class FeedItemMapper @Inject constructor() :
    Mapper<FeedQuery.Data, List<FeedItem<*>>> {

    override suspend fun map(model: FeedQuery.Data): List<FeedItem<*>> =
        model.feedItems.nodes
            .mapNotNull { it.asVideoFeedItem }
            .map { FeedItem(provider = model.provider, item = it) }
}