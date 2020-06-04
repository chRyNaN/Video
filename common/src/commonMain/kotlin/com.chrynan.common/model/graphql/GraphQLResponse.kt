package com.chrynan.common.model.graphql

data class GraphQLResponse<T>(
    val data: T? = null,
    val errors: List<GraphQLError> = emptyList()
) {

    val isError: Boolean
        get() = data == null
}