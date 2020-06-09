package com.chrynan.common.api.query

import com.chrynan.common.Inject
import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.graphql.GraphQLRequest

class SearchGraphQLQuery @Inject constructor() {

    operator fun invoke(searchItem: String, take: Int, after: Cursor? = null): GraphQLRequest {
        val query = """
            query SearchQuery(${'$'}searchItem: String!, ${'$'}take: Int!, ${'$'}after: Cursor) {
                search(query: ${'$'}searchItem, take: ${'$'}take, after: ${'$'}after) {
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
                            ... on VideoSearchResultItem {
                                __typename
                                channel {
                                    ...channelFields
                                }
                                video {
                                    ...videoFields
                                }
                            }
                            ... on ChannelSearchResultItem {
                                __typename
                                channel {
                                    ...channelFields
                                }
                            }
                        }
                    }
                    nodes {
                        ... on VideoSearchResultItem {
                            __typename
                            channel {
                                ...channelFields
                            }
                            video {
                                ...videoFields
                            }
                        }
                        ... on ChannelSearchResultItem {
                            __typename
                            channel {
                                ...channelFields
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

        val variables =
            mapOf<String, Any?>("searchItem" to searchItem, "take" to take, "after" to after)

        return GraphQLRequest(
            query = query,
            operationName = "SearchQuery",
            variables = variables
        )
    }
}