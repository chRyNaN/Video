package com.chrynan.common.graphql

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.flow.Flow

interface GraphQLClient {

    fun <D : Operation.Data, T, V : Operation.Variables> query(query: Query<D, T, V>): Flow<Response<T>>
}