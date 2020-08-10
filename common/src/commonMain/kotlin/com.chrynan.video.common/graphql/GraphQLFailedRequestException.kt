package com.chrynan.video.common.graphql

import com.apollographql.apollo.api.Error

/**
 * A Kotlin [RuntimeException] that is thrown when executing a GraphQL Request which results in
 * data that equals null and an [Error]. If the a GraphQL Request returns a non-null data but there
 * are still [Error]s, then it is recommended to simply log the errors and continue using the data
 * as if there was no issue.
 */
class GraphQLFailedRequestException(errors: List<Error>) :
    RuntimeException("GraphQL errors received and can't parse response. errors = $errors")

@Suppress("FunctionName")
fun GraphQLFailedRequestException(vararg errors: Error) =
    GraphQLFailedRequestException(errors.toList())