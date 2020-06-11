package com.chrynan.video.di.module

import com.chrynan.common.api.ApiService
import com.chrynan.common.mapper.FeedResultItemMapper
import com.chrynan.common.mapper.SearchResultItemMapper
import com.chrynan.common.repository.*
import com.chrynan.common.repository.database.ServiceProviderDatabaseRepository
import com.chrynan.common.repository.source.*
import com.chrynan.video.coroutine.RepositoryCoroutineScope
import com.chrynan.video.database.source.ServiceProviderDatabaseSource
import com.chrynan.video.source.AppInfoSource
import com.chrynan.video.source.SettingsInfoSource
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
            webApi: ApiService,
            serviceRepository: ServiceProviderRepository,
            mapper: FeedResultItemMapper,
            coroutineScope: RepositoryCoroutineScope
        ): FeedItemRepository = FeedItemRepositorySource(
            webApi = webApi,
            serviceRepository = serviceRepository,
            mapper = mapper,
            coroutineScope = coroutineScope
        )

        @Provides
        @JvmStatic
        @OptIn(ExperimentalCoroutinesApi::class)
        fun provideSearchItemRepository(
            webApi: ApiService,
            serviceRepository: ServiceProviderRepository,
            mapper: SearchResultItemMapper,
            coroutineScope: RepositoryCoroutineScope
        ): SearchItemRepository = SearchItemRepositorySource(
            webApi = webApi,
            serviceRepository = serviceRepository,
            mapper = mapper,
            coroutineScope = coroutineScope
        )
    }

    @Binds
    abstract fun bindServiceDatabaseRepository(source: ServiceProviderDatabaseSource): ServiceProviderDatabaseRepository

    @Binds
    abstract fun bindServiceRepository(source: ServiceProviderSource): ServiceProviderRepository

    @Binds
    abstract fun bindLoginInfoRepository(source: LoginInfoRepositorySource): LoginInfoRepository

    @Binds
    abstract fun bindAppInfoRepository(source: AppInfoSource): AppInfoRepository

    @Binds
    abstract fun bindSettingsInfoRepository(source: SettingsInfoSource): SettingsInfoRepository

    @Binds
    abstract fun bindTagSuggestionRepository(source: TagSuggestionRepositorySource): TagSuggestionRepository

    @Binds
    abstract fun bindChannelRepository(source: ChannelRepositorySource): ChannelRepository
}