package com.chrynan.common.model.response

import com.chrynan.common.model.api.FeedItemConnection
import com.chrynan.common.model.api.Provider

data class FeedResponse(
    val provider: Provider,
    val connection: FeedItemConnection
)