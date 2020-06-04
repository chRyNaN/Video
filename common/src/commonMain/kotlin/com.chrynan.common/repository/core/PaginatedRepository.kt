package com.chrynan.common.repository.core

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.core.Edge
import com.chrynan.common.model.core.Node
import kotlinx.coroutines.flow.Flow

interface PaginatedRepository<N : Node, E : Edge<N>, C : Connection<N, E>> {

    val currentConnection: C?

    fun subscribe(first: Int = 10, after: Cursor? = null): Flow<List<N>>

    suspend fun load(next: Int)
}