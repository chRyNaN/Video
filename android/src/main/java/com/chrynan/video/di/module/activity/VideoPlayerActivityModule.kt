package com.chrynan.video.di.module.activity

import com.chrynan.video.common.repository.VideoRepository
import com.chrynan.video.coroutine.ActivityCoroutineScope
import com.chrynan.video.di.module.fragment.OpenVideoDetailsFragmentModule
import com.chrynan.video.di.module.fragment.VideoPlayerFragmentModule
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.common.provider.OpenVideoProvider
import com.chrynan.video.di.module.fragment.GenericContentVideoDetailsFragmentModule
import com.chrynan.video.di.module.fragment.LbryVideoDetailsFragmentModule
import com.chrynan.video.presentation.navigator.Navigator
import com.chrynan.video.presentation.navigator.VideoScreen
import com.chrynan.video.provider.source.OpenVideoProviderSource
import com.chrynan.video.ui.activity.VideoPlayerActivity
import com.chrynan.video.ui.fragment.GenericContentVideoDetailsFragment
import com.chrynan.video.ui.fragment.LbryVideoDetailsFragment
import com.chrynan.video.ui.fragment.OpenVideoDetailsFragment
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

    @Binds
    @ActivityScope
    abstract fun bindVideoPlayerActivityNavigator(activity: VideoPlayerActivity): Navigator<VideoScreen>

    @FragmentScope
    @ContributesAndroidInjector(modules = [VideoPlayerFragmentModule::class])
    abstract fun videoPlayerFragmentInjector(): VideoPlayerFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [OpenVideoDetailsFragmentModule::class])
    abstract fun openVideoDetailsFragmentInjector(): OpenVideoDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [GenericContentVideoDetailsFragmentModule::class])
    abstract fun genericContentVideoDetailsFragmentInjector(): GenericContentVideoDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [LbryVideoDetailsFragmentModule::class])
    abstract fun lbryVideoDetailsFragmentInjectory(): LbryVideoDetailsFragment
}