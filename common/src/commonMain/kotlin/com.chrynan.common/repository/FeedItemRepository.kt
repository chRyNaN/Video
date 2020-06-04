package com.chrynan.common.repository

import com.chrynan.common.model.FeedItem
import com.chrynan.common.model.FeedItemConnection
import com.chrynan.common.model.FeedItemEdge
import com.chrynan.common.repository.core.PaginatedRepository

interface FeedItemRepository : PaginatedRepository<FeedItem, FeedItemEdge, FeedItemConnection>