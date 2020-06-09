package com.chrynan.common.repository.core

interface StatefulFlowRepository<T> {

    val value: T

    suspend fun loadMore()

    fun canLoadMore(): Boolean
}