package com.chrynan.common.repository

import com.chrynan.common.model.api.FeedItem
import kotlinx.coroutines.flow.Flow

interface FeedItemRepository {

    fun getFeedItems(): Flow<List<FeedItem>>
}