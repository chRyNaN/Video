package com.chrynan.video.common.repository

import com.chrynan.video.common.model.api.FeedItem
import kotlinx.coroutines.flow.Flow

interface FeedItemRepository {

    companion object {

        private const val DEFAULT_PROVIDER_TAKE_COUNT = 10
    }

    fun get(): Flow<List<FeedItem<*>>?>

    suspend fun refresh()

    suspend fun loadMore(count: Int = DEFAULT_PROVIDER_TAKE_COUNT)
}