package com.chrynan.common.repository

import com.chrynan.common.model.wrapper.SearchResultItemWrapper
import com.chrynan.common.repository.core.StatefulFlowRepository
import kotlinx.coroutines.flow.Flow

interface SearchItemRepository : StatefulFlowRepository<List<SearchResultItemWrapper>> {

    fun search(query: String): Flow<List<SearchResultItemWrapper>>
}