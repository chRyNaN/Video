package com.chrynan.common.repository.source

import FeedQuery
import com.apollographql.apollo.api.Input
import com.chrynan.common.Inject
import com.chrynan.common.graphql.GraphQLClientFactory
import com.chrynan.common.graphql.filterSuccess
import com.chrynan.common.mapper.FeedItemMapper
import com.chrynan.common.model.api.FeedItem
import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.core.UriString
import com.chrynan.common.paginate.CursorCache
import com.chrynan.common.paginate.CursorCacheValue
import com.chrynan.common.repository.FeedItemRepository
import com.chrynan.common.repository.ServiceProviderRepository
import com.chrynan.common.utils.firstAsFlow
import com.chrynan.logger.Logger
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
                    var cursorValue = cursorCache[""]

                    if (cursorValue == null) {
                        cursorValue = CursorCacheValue(cursor = null, hasNextPage = true)

                        cursorCache[""] = cursorValue
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
            .filterSuccess { Logger.logWarning(message = "Errors fetching feed items for providerUri = $providerUri; errors = $it") }
            .map { mapper.map(it) }
    }
}