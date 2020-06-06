package com.chrynan.common.model.graphql

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GraphQLResponse<T>(
    @SerialName(value = "data") val data: T? = null,
    @SerialName(value = "errors") val errors: List<GraphQLError> = emptyList()
) {

    val isError: Boolean
        get() = data == null
}