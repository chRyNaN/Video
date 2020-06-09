package com.chrynan.common.model.response

import com.chrynan.common.model.api.Provider
import com.chrynan.common.model.api.SearchResultItemConnection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName(value = "provider") val provider: Provider,
    @SerialName(value = "search") val connection: SearchResultItemConnection
)