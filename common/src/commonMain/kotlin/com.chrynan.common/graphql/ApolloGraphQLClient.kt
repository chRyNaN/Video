package com.chrynan.common.graphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@OptIn(ApolloExperimental::class, ExperimentalCoroutinesApi::class)
class ApolloGraphQLClient(private val apolloClient: ApolloClient) : GraphQLClient {

    override fun <D : Operation.Data, T, V : Operation.Variables> query(query: Query<D, T, V>): Flow<Response<T>> =
        apolloClient.query(query).execute()
}