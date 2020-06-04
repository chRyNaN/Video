package com.chrynan.common.api.query

data class GraphQLQuery(
    val queryString: String,
    val headers: Map<String, Any?> = emptyMap()
)