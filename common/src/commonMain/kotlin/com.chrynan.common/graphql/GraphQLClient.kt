package com.chrynan.common.graphql

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.flow.Flow

interface GraphQLClient {

    fun <D : Operation.Data, V : Operation.Variables> query(query: Query<D, D, V>): Flow<Response<D>>
}