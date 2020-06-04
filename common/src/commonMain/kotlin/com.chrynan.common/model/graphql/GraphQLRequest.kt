package com.chrynan.common.model.graphql

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GraphQLRequest(
    @SerialName(value = "query") val query: String,
    @SerialName(value = "operationName") val operationName: String? = null,
    @SerialName(value = "variables") val variables: Map<String, @ContextualSerialization Any?> = emptyMap() // TODO verify serialization works correctly
)