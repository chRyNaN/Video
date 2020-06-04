package com.chrynan.common.api

import com.chrynan.common.Inject
import com.chrynan.common.api.query.FeedGraphQLQuery
import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.core.UriString
import com.chrynan.common.model.graphql.GraphQLRequest
import com.chrynan.common.model.graphql.GraphQLResponse
import com.chrynan.common.model.response.FeedResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.post

class WebApi @Inject constructor(
    private val client: HttpClient,
    private val feedGraphQLQuery: FeedGraphQLQuery
) {

    companion object {

        private const val API_ENDPOINT = "graphql"
    }

    suspend fun getFeed(
        providerUri: UriString,
        token: String? = null,
        take: Int,
        after: Cursor? = null
    ): GraphQLResponse<FeedResponse> {
        val request = feedGraphQLQuery(take = take, after = after)

        return client.graphQLRequest(providerUri = providerUri, request = request) {
            header("Authorization", token)
        }
    }

    private suspend inline fun <reified T> HttpClient.graphQLRequest(
        providerUri: UriString,
        request: GraphQLRequest,
        block: HttpRequestBuilder.() -> Unit = {}
    ) = post<T>("$providerUri/$API_ENDPOINT") {
        block()
        body = request
    }
}