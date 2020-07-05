package com.chrynan.common.graphql

import com.apollographql.apollo.api.Error
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.flow.*

fun <T : Any> Flow<Response<T>>.filterSuccess(onGraphQLError: ((List<Error>) -> Unit)? = null): Flow<T> =
    onEach {
        val errors = it.errors

        if (!errors.isNullOrEmpty()) {
            onGraphQLError?.invoke(errors)
        }
    }.mapNotNull { it.data }