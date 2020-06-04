package com.chrynan.common.api

import com.chrynan.common.Inject
import com.chrynan.common.model.core.Cursor

class GraphQLQueries @Inject constructor() {

    fun feedQuery(take: Int, after: Cursor? = null): GraphQLQuery {
        val stringQuery =
            """
                query FeedQuery(${'$'}take: Int!, ${'$'}after: Cursor) {
                    feed(take: ${'$'}take, after: ${'$'}after) {
                        pageInfo {
                
                        }
                        totalCount
                        edges {
                
                        }
                        nodes {
                
                        }
                    }
                }
            """.trimIndent()

        val headers = mapOf<String, Any?>("take" to take, "after" to after)

        return GraphQLQuery(stringQuery, headers)
    }
}