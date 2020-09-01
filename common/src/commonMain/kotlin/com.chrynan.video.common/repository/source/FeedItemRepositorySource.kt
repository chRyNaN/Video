package com.chrynan.video.common.repository.source

import FeedQuery
import com.apollographql.apollo.api.Input
import com.chrynan.inject.Inject
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class FeedItemRepositorySource @Inject constructor(
    private val providerRepository: ServiceProviderRepository,
    private val graphQLClientFactory: GraphQLClientFactory,
    private val cursorCache: CursorCache,
    private val mapper: FeedItemMapper
) : FeedItemRepository {

    private val items = mutableListOf<FeedItem<*>>()
    private var stateFlow = MutableStateFlow<List<FeedItem<*>>?>(null)

    override fun get(): Flow<List<FeedItem<*>>?> =
        stateFlow.onStart { if (stateFlow.value == null) loadMore() }

    override suspend fun refresh() {
        items.clear()
        stateFlow.value = null
        loadMore()
    }

    override suspend fun loadMore(count: Int) {
        coroutineScope {
            providerRepository.getAll()
                .first()
                .map {
                    async {
                        val cursorValue = getCursorValueForProviderUri(providerUri = it.providerUri)

                        getFeedItemsForProviderUri(
                            providerUri = it.providerUri,
                            take = count,
                            after = cursorValue.cursor
                        )
                    }
                }
                .forEach {
                    val newItems = it.await()

                    items.addAll(newItems)

                    stateFlow.value = items
                }
        }
    }

    private fun getCursorValueForProviderUri(providerUri: UriString): CursorCacheValue {
        var cursorValue = cursorCache[providerUri]

        if (cursorValue == null) {
            cursorValue =
                CursorCacheValue(
                    cursor = null,
                    hasNextPage = true
                )

            cursorCache[providerUri] = cursorValue
        }

        return cursorValue
    }

    private fun updateCursorValueForProviderUri(providerUri: UriString, data: FeedQuery.Data) {
        cursorCache[providerUri] = CursorCacheValue(
            cursor = data.feedItems.pageInfo.fragments.pageInfoFragment.endCursor,
            hasNextPage = data.feedItems.pageInfo.fragments.pageInfoFragment.hasNextPage
        )
    }

    private suspend fun getFeedItemsForProviderUri(
        providerUri: UriString,
        take: Int,
        after: Cursor? = null
    ): List<FeedItem<*>> {
        val client = graphQLClientFactory.getOrCreate(providerUri)

        val query = FeedQuery(take, Input.fromNullable(after))

        return client.query(query)
            .filterSuccess()
            .onEach { updateCursorValueForProviderUri(providerUri = providerUri, data = it) }
            .map { mapper.map(it) }
            .first()
    }
}