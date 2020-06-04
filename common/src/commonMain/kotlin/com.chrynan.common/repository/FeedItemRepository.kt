package com.chrynan.common.repository

import com.chrynan.common.model.result.FeedResultItem
import com.chrynan.common.repository.core.StatefulFlowRepository

interface FeedItemRepository : StatefulFlowRepository<List<FeedResultItem>>