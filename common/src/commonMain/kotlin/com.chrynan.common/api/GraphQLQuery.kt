package com.chrynan.common.api

data class GraphQLQuery(
    val queryString: String,
    val headers: Map<String, Any?> = emptyMap()
)