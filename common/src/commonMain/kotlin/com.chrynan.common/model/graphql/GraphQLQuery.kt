package com.chrynan.common.model.graphql

data class GraphQLQuery(
    val queryString: String,
    val headers: Map<String, Any?> = emptyMap()
)