package com.chrynan.common.api

import com.chrynan.common.Inject
import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.core.UriString
import com.chrynan.common.model.graphql.GraphQLResponse
import com.chrynan.common.model.response.FeedResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header

class WebApi @Inject constructor(private val client: HttpClient) {

    suspend fun getFeed(
        providerUri: UriString,
        token: String? = null,
        take: Int,
        after: Cursor? = null
    ): GraphQLResponse<FeedResponse> =
        client.get {
            header("", token)


        }
}