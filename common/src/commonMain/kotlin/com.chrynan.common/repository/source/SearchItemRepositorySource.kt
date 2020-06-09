package com.chrynan.common.repository.source

import com.chrynan.common.api.ApiService
import com.chrynan.common.mapper.SearchResultItemMapper
import com.chrynan.common.model.core.PageInfo
import com.chrynan.common.model.core.UriString
import com.chrynan.common.model.wrapper.SearchResultItemWrapper
import com.chrynan.common.repository.SearchItemRepository
import com.chrynan.common.repository.ServiceProviderRepository
import com.chrynan.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart

@ExperimentalCoroutinesApi
class SearchItemRepositorySource(
    private val webApi: ApiService,
    private val serviceRepository: ServiceProviderRepository,
    private val mapper: SearchResultItemMapper,
    private val coroutineScope: CoroutineScope
) : SearchItemRepository {

    companion object {

        private const val DEFAULT_TAKE_COUNT = 10
    }

    override val value: List<SearchResultItemWrapper>
        get() = mutableStateFlow.value

    private val mutableStateFlow = MutableStateFlow(emptyList<SearchResultItemWrapper>())

    private val pageInfoMap = mutableMapOf<UriString, PageInfo?>()

    private var isLoading = false

    private var query: String? = null

    override fun search(query: String): Flow<List<SearchResultItemWrapper>> {
        isLoading = false
        this.query = query
        mutableStateFlow.value = emptyList()
        pageInfoMap.clear()

        return mutableStateFlow.onStart { if (!isLoading && pageInfoMap.isEmpty()) loadMore() }
    }

    override suspend fun loadMore() {
        if (!isLoading) {
            isLoading = true

            val services = serviceRepository.getAll().first()

            services.map { service ->
                coroutineScope.async {
                    val pageInfo = pageInfoMap[service.providerUri]

                    if (pageInfo.canLoadMore()) {
                        val response = webApi.getSearchResults(
                            query = query ?: "",
                            providerUri = service.providerUri,
                            token = service.token,
                            take = DEFAULT_TAKE_COUNT,
                            after = pageInfo?.endCursor
                        )

                        pageInfoMap[service.providerUri] = response.data?.connection?.pageInfo

                        if (!response.isError && response.data != null) {
                            val items = mapper.map(response.data)

                            mutableStateFlow.value = mutableStateFlow.value + items
                        } else {
                            Logger.logError("Error fetching feed for Service Provider: ${service.providerUri}. Errors: ${response.errors}")
                        }
                    }
                }
            }.forEach {
                try {
                    it.await()
                } catch (throwable: Throwable) {
                    Logger.logError(
                        throwable = throwable,
                        message = "Error fetching feed for Service Provider."
                    )
                }
            }

            isLoading = false
        }
    }

    override fun canLoadMore(): Boolean =
        pageInfoMap.entries.any { it.value.canLoadMore() } && query != null

    private fun PageInfo?.canLoadMore() = this?.hasNextPage ?: true
}