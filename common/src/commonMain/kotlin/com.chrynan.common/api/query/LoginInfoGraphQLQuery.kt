package com.chrynan.common.api.query

import com.chrynan.common.Inject
import com.chrynan.common.model.graphql.GraphQLRequest

class LoginInfoGraphQLQuery @Inject constructor() {

    operator fun invoke(): GraphQLRequest {
        val query = """
            query LoginInfoQuery {
                apiVersion
                login {
                    type
                    loginUri
                    requestId
                }
                provider {
                    created
                    lastUpdated
                    uri
                    name
                    images {
                        thumbnail
                    }
                }
            }
        """.trimIndent()

        return GraphQLRequest(
            query = query,
            operationName = "LoginInfoQuery",
            variables = emptyMap()
        )
    }
}