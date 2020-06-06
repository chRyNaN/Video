package com.chrynan.common.model.response

import com.chrynan.common.model.api.FeedItemConnection
import com.chrynan.common.model.api.Provider
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedResponse(
    @SerialName(value = "provider") val provider: Provider,
    @SerialName(value = "connection") val connection: FeedItemConnection
)