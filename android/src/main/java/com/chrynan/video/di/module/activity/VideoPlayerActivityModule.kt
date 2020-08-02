package com.chrynan.video.di.module.activity

import com.chrynan.common.repository.VideoRepository
import com.chrynan.video.coroutine.ActivityCoroutineScope
import com.chrynan.video.di.module.fragment.VideoDetailsFragmentModule
import com.chrynan.video.di.module.fragment.VideoPlayerFragmentModule
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.common.provider.OpenVideoProvider
import com.chrynan.video.provider.source.OpenVideoProviderSource
import com.chrynan.video.ui.activity.VideoPlayerActivity
import com.chrynan.video.ui.fragment.VideoDetailsFragment
import com.chrynan.video.ui.fragment.VideoPlayerFragment
import com.chrynan.video.utils.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class VideoPlayerActivityModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @ActivityScope
        fun provideOpenVideoProvider(
            videoRepository: VideoRepository,
            coroutineScope: ActivityCoroutineScope
        ): OpenVideoProvider = OpenVideoProviderSource(
            videoRepository = videoRepository,
            coroutineScope = coroutineScope
        )
    }

    @Binds
    @ActivityScope
    @ActivityContextQualifier
    abstract fun bindActivityContext(activity: VideoPlayerActivity): ActivityContext

    @Binds
    @ActivityScope
    abstract fun bindActivityCoroutineScope(activity: VideoPlayerActivity): ActivityCoroutineScope

    @FragmentScope
    @ContributesAndroidInjector(modules = [VideoPlayerFragmentModule::class])
    abstract fun videoFragmentInjector(): VideoPlayerFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [VideoDetailsFragmentModule::class])
    abstract fun videoDetailsInjector(): VideoDetailsFragment
}