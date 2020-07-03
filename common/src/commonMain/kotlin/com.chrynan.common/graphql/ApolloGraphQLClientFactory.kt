package com.chrynan.common.graphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.network.ApolloHttpNetworkTransport
import com.chrynan.common.Inject
import com.chrynan.common.model.core.UriString
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ApolloExperimental::class, ExperimentalCoroutinesApi::class)
class ApolloGraphQLClientFactory @Inject constructor() : GraphQLClientFactory {

    override fun create(baseUri: UriString): GraphQLClient {
        val apolloClient = ApolloClient(
            networkTransport = ApolloHttpNetworkTransport(
                serverUrl = "https://api.github.com/graphql",
                headers = mapOf(
                    "Accept" to "application/json",
                    "Content-Type" to "application/json"
                )
            ),
            scalarTypeAdapters = ScalarTypeAdapters(
                customAdapters = mapOf() // TODO add custom Scalars
            )
        )

        return ApolloGraphQLClient(apolloClient)
    }
}
