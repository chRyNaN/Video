package com.chrynan.video.di.module

import com.chrynan.common.api.WebApi
import com.chrynan.common.mapper.FeedResultItemMapper
import com.chrynan.common.repository.FeedItemRepository
import com.chrynan.common.repository.ServiceRepository
import com.chrynan.common.repository.source.FeedItemRepositorySource
import com.chrynan.video.coroutine.RepositoryCoroutineScope
import com.chrynan.video.database.source.ServiceRepositorySource
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
internal abstract class RepositoryModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @OptIn(ExperimentalCoroutinesApi::class)
        fun provideFeedItemRepository(
            webApi: WebApi,
            serviceRepository: ServiceRepository,
            mapper: FeedResultItemMapper,
            coroutineScope: RepositoryCoroutineScope
        ): FeedItemRepository = FeedItemRepositorySource(
            webApi = webApi,
            serviceRepository = serviceRepository,
            mapper = mapper,
            coroutineScope = coroutineScope
        )
    }

    @Binds
    abstract fun bindServiceRepository(source: ServiceRepositorySource): ServiceRepository
}