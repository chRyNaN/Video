package com.chrynan.common.model.api

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SearchResultItemConnection(
    @SerialName(value = "totalCount") override val totalCount: Int = 0,
    @SerialName(value = "pageInfo") override val pageInfo: PageInfo = PageInfo(),
    @SerialName(value = "edges") override val edges: List<SearchResultItemEdge> = emptyList(),
    @SerialName(value = "nodes") override val nodes: List<SearchResultItem> = emptyList()
) : Connection<SearchResultItem, SearchResultItemEdge>