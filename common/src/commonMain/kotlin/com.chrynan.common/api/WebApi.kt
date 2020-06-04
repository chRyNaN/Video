package com.chrynan.common.api

import com.chrynan.common.Inject
import com.chrynan.common.model.FeedItemConnection
import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.core.UriString
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header

class WebApi @Inject constructor(private val client: HttpClient) {

    suspend fun getFeed(
        baseUri: UriString,
        token: String,
        take: Int,
        after: Cursor? = null
    ): FeedItemConnection =
        client.get {
            header("", token)


        }
}