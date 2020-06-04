package com.chrynan.common.repository.core

import kotlinx.coroutines.flow.Flow

interface StatefulFlowRepository<T> {

    fun openSubscription(): Flow<T>

    suspend fun loadMore()

    fun canLoadMore(): Boolean
}