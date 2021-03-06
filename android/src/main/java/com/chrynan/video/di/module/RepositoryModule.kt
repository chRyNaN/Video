package com.chrynan.video.di.module

import com.chrynan.video.common.paginate.CursorCache
import com.chrynan.video.common.paginate.MapCursorCache
import com.chrynan.video.common.repository.database.ServiceProviderDatabaseRepository
import com.chrynan.video.common.repository.*
import com.chrynan.video.common.repository.source.*
import com.chrynan.video.database.source.ServiceProviderDatabaseSource
import com.chrynan.video.source.AppInfoSource
import com.chrynan.video.source.SettingsInfoSource
import dagger.Binds
import dagger.Module

@Module
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindCursorCache(mapCursorCache: MapCursorCache): CursorCache

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

    @Binds
    abstract fun bindFeedItemRepository(source: FeedItemRepositorySource): FeedItemRepository

    @Binds
    abstract fun bindSearchItemRepository(source: SearchItemRepositorySource): SearchItemRepository

    @Binds
    abstract fun bindVideoRepository(source: VideoRepositorySource): VideoRepository
}