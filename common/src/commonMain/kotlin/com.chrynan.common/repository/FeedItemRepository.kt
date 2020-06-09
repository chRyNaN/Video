package com.chrynan.common.repository

import com.chrynan.common.model.wrapper.FeedItemWrapper
import com.chrynan.common.repository.core.StatefulFlowRepository
import kotlinx.coroutines.flow.Flow

interface FeedItemRepository : StatefulFlowRepository<List<FeedItemWrapper>> {

    fun openSubscription(): Flow<List<FeedItemWrapper>>
}