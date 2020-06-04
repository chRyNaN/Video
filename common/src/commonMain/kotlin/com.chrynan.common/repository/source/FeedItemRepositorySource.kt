package com.chrynan.common.repository.source

import com.chrynan.common.Inject
import com.chrynan.common.api.WebApi
import com.chrynan.common.mapper.FeedResultItemMapper
import com.chrynan.common.model.core.PageInfo
import com.chrynan.common.model.core.UriString
import com.chrynan.common.model.result.FeedResultItem
import com.chrynan.common.repository.FeedItemRepository
import com.chrynan.common.repository.ServiceRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class FeedItemRepositorySource @Inject constructor(
    private val webApi: WebApi,
    private val serviceRepository: ServiceRepository,
    private val mapper: FeedResultItemMapper
) : FeedItemRepository {

    private val mutableStateFlow = MutableStateFlow(emptyList<FeedResultItem>())

    private val pageInfoMap = mutableMapOf<UriString, PageInfo?>()

    override fun openSubscription(): Flow<List<FeedResultItem>> = mutableStateFlow

    override suspend fun loadMore() {
        val services = serviceRepository.getAll()

        for (service in services) {
            val pageInfo = pageInfoMap[service.providerUri]

            if (pageInfo.canLoadMore()) {
                // TODO update to load these using async block
                val response = webApi.getFeed(
                    providerUri = service.providerUri,
                    token = service.token,
                    take = 10,
                    after = pageInfo?.endCursor
                )

                pageInfoMap[service.providerUri] = response.data?.connection?.pageInfo

                if (!response.isError && response.data != null) {
                    val items = mapper.map(response.data)

                    mutableStateFlow.value = mutableStateFlow.value + items
                }
            }
        }
    }

    override fun canLoadMore(): Boolean = pageInfoMap.entries.any { it.value.canLoadMore() }

    private fun PageInfo?.canLoadMore() = this?.hasNextPage ?: true
}