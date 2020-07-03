package com.chrynan.common.api

import FeedQuery
import com.apollographql.apollo.api.Input
import com.chrynan.common.Inject
import com.chrynan.common.api.query.ChannelGraphQLQuery
import com.chrynan.common.api.query.FeedGraphQLQuery
import com.chrynan.common.api.query.LoginInfoGraphQLQuery
import com.chrynan.common.api.query.SearchGraphQLQuery
import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString
import com.chrynan.common.model.graphql.GraphQLResponse
import com.chrynan.common.model.response.ChannelResponse
import com.chrynan.common.model.response.FeedResponse
import com.chrynan.common.model.response.LoginInfoResponse
import com.chrynan.common.model.response.SearchResponse
import com.chrynan.common.utils.graphQLRequest
import io.ktor.client.HttpClient

class ApiService @Inject constructor(
    private val client: HttpClient,
    private val feedGraphQLQuery: FeedGraphQLQuery,
    private val loginInfoGraphQLQuery: LoginInfoGraphQLQuery,
    private val channelQuery: ChannelGraphQLQuery,
    private val searchQuery: SearchGraphQLQuery
) {

    suspend fun getLoginInfo(providerUri: UriString): GraphQLResponse<LoginInfoResponse> {
        val request = loginInfoGraphQLQuery()

        return client.graphQLRequest(providerUri = providerUri, request = request)
    }

    suspend fun getFeed(
        providerUri: UriString,
        token: String? = null,
        take: Int,
        after: Cursor? = null
    ): GraphQLResponse<FeedResponse> {
        val request = feedGraphQLQuery(take = take, after = after)

        return client.graphQLRequest(providerUri = providerUri, request = request, token = token)
    }

    suspend fun getSearchResults(
        query: String,
        providerUri: UriString,
        token: String? = null,
        take: Int,
        after: Cursor? = null
    ): GraphQLResponse<SearchResponse> {
        val request = searchQuery(searchItem = query, take = take, after = after)

        return client.graphQLRequest(providerUri = providerUri, request = request, token = token)
    }

    suspend fun getChannelById(
        providerUri: UriString,
        channelID: ID,
        token: String? = null
    ): GraphQLResponse<ChannelResponse> {
        val request = channelQuery(channelID = channelID)

        return client.graphQLRequest(providerUri = providerUri, request = request, token = token)
    }
}