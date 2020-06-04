package com.chrynan.common.repository.web

import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.response.FeedResponse

interface FeedItemWebRepository {

    fun subscribe(first: Int = 10, after: Cursor? = null): FeedResponse
}