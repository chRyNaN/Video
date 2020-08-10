package com.chrynan.video.common.repository.source

import FeedQuery
import com.apollographql.apollo.api.Input
import com.chrynan.video.common.Inject
import com.chrynan.video.common.graphql.GraphQLClientFactory
import com.chrynan.video.common.graphql.filterSuccess
import com.chrynan.video.common.mapper.FeedItemMapper
import com.chrynan.video.common.model.api.FeedItem
import com.chrynan.video.common.model.core.Cursor
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.common.paginate.CursorCache
import com.chrynan.video.common.paginate.CursorCacheValue
import com.chrynan.video.common.repository.FeedItemRepository
import com.chrynan.video.common.repository.ServiceProviderRepository
import com.chrynan.video.common.utils.firstAsFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

class FeedItemRepositorySource @Inject constructor(
    private val providerRepository: ServiceProviderRepository,
    private val graphQLClientFactory: GraphQLClientFactory,
    private val cursorCache: CursorCache,
    private val mapper: FeedItemMapper
) : FeedItemRepository {

    companion object {

        private const val DEFAULT_PROVIDER_TAKE_COUNT = 10
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    override fun getFeedItems(): Flow<List<FeedItem>> =
        providerRepository.getAll()
            .firstAsFlow()
            .flatMapConcat {
                val flows = it.map { serviceProvider ->
                    var cursorValue = cursorCache[serviceProvider.providerUri]

                    if (cursorValue == null) {
                        cursorValue =
                            CursorCacheValue(
                                cursor = null,
                                hasNextPage = true
                            )

                        cursorCache[serviceProvider.providerUri] = cursorValue
                    }

                    getFeedItemsForProviderUri(
                        providerUri = serviceProvider.providerUri,
                        take = DEFAULT_PROVIDER_TAKE_COUNT,
                        after = cursorValue.cursor
                    )
                }

                merge(*flows.toTypedArray())
            }

    private fun getFeedItemsForProviderUri(
        providerUri: UriString,
        take: Int,
        after: Cursor? = null
    ): Flow<List<FeedItem>> {
        val client = graphQLClientFactory.getOrCreate(providerUri)

        val query = FeedQuery(take, Input.fromNullable(after))

        return client.query(query)
            .filterSuccess()
            .map { mapper.map(it) }
    }
}