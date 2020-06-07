package com.chrynan.common.api.query

import com.chrynan.common.Inject
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.graphql.GraphQLRequest

class ChannelQuery @Inject constructor() {

    operator fun invoke(channelID: ID): GraphQLRequest {
        val query = """
            query ChannelQuery(${'$'}channelID: ID!) {
                provider {
                    created
                    lastUpdated
                    uri
                    name
                    channel(id: ${'$'}channelID) {
                        id
                        created
                        lastUpdated
                        published
                        name
                        description
                        about
                        website
                        images {
                            thumbnail
                            banner
                        }
                        count {
                            totalSubscribers
                            totalVideoViews
                        }
                        isSubscribed
                        lists {
                            id
                            name
                            totalCount
                            pageInfo {
                                startCursor
                                endCursor
                                hasNextPage
                                hasPreviousPage
                            }
                            nodes {
                                id
                                created
                                lastUpdated
                                published
                                uri
                                name
                                isLive
                                lengthInMilliseconds
                                previewImage
                            }
                        }
                    }
                }
            }
        """.trimIndent()

        val variables = mapOf("channelID" to channelID)

        return GraphQLRequest(
            query = query,
            operationName = "ChannelQuery",
            variables = variables
        )
    }
}