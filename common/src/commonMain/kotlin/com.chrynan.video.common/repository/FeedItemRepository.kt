package com.chrynan.video.common.repository

import com.chrynan.video.common.model.api.FeedItem
import kotlinx.coroutines.flow.Flow

interface FeedItemRepository {

    fun getFeedItems(): Flow<List<FeedItem>>
}