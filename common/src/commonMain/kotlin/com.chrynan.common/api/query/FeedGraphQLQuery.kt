package com.chrynan.common.api.query

import com.chrynan.common.Inject
import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.graphql.GraphQLRequest

class FeedGraphQLQuery @Inject constructor() {

    operator fun invoke(take: Int, after: Cursor? = null): GraphQLRequest {
        val query =
            """
                query FeedQuery(${'$'}take: Int!, ${'$'}after: Cursor) {
                    feed(take: ${'$'}take, after: ${'$'}after) {
                        pageInfo {
                            startCursor
                            endCursor
                            hasNextPage
                            hasPreviousPage
                        }
                        totalCount
                        edges {
                            cursor
                            node {
                                ... on VideoFeedItem {
                                    __typename
                                    channel {
                                        ...channelFields
                                    }
                                    video {
                                        ..videoFields
                                    }
                                }
                            }
                        }
                        nodes {
                            ... on VideoFeedItem {
                                __typename
                                channel {
                                    ...channelFields
                                }
                                video {
                                    ..videoFields
                                }
                            }
                        }
                    }
                    provider {
                        name
                        uri
                        created
                        lastUpdated
                    }
                }
                
                fragment channelFields on Channel {
                    id
                    created
                    lastUpdated
                    published
                    name
                    images {
                        thumbnail
                    }
                    count {
                        totalSubscribers
                        totalVideoViews
                    }
                    isSubscribed
                }
                
                fragment videoFields on Video {
                    id
                    created
                    lastUpdated
                    published
                    uri
                    name
                    description
                    previewImage
                    viewCount
                    isLive
                    lengthInMilliseconds
                    streamType
                }
            """.trimIndent()

        val variables = mapOf<String, Any?>("take" to take, "after" to after)

        return GraphQLRequest(
            query = query,
            operationName = "FeedQuery",
            variables = variables
        )
    }
}