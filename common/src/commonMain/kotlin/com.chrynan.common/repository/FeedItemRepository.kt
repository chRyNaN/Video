package com.chrynan.common.repository

import com.chrynan.common.model.api.FeedItem
import com.chrynan.common.model.api.FeedItemConnection
import com.chrynan.common.model.api.FeedItemEdge
import com.chrynan.common.repository.core.PaginatedRepository

interface FeedItemRepository : PaginatedRepository<FeedItem, FeedItemEdge, FeedItemConnection>