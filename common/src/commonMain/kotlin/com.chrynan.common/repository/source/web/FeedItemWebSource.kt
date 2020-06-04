package com.chrynan.common.repository.source.web

import com.chrynan.common.model.FeedItem
import com.chrynan.common.model.FeedItemConnection
import com.chrynan.common.model.core.Cursor
import com.chrynan.common.repository.FeedItemRepository
import kotlinx.coroutines.flow.Flow

class FeedItemWebSource(
    baseUrl: String
) : FeedItemRepository {

    override val currentConnection: FeedItemConnection?
        get() = TODO("Not yet implemented")

    override fun subscribe(first: Int, after: Cursor?): Flow<List<FeedItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun load(next: Int) {
        TODO("Not yet implemented")
    }
}