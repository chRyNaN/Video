package com.chrynan.video.common.graphql

import com.apollographql.apollo.api.Response
import com.chrynan.logger.Logger
import kotlinx.coroutines.flow.*

fun <T : Any> Flow<Response<T>>.filterSuccess(): Flow<T> =
    mapNotNull {
        val errors = it.errors

        if (it.data == null) throw GraphQLFailedRequestException(
            it.errors ?: emptyList()
        )

        if (!errors.isNullOrEmpty()) {
            Logger.logWarning(message = "Errors received from GraphQL request. errors = $errors")
        }

        it.data
    }