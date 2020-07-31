package com.chrynan.video.di.module.activity

import com.chrynan.video.di.module.fragment.VideoDetailsFragmentModule
import com.chrynan.video.di.module.fragment.VideoPlayerFragmentModule
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.activity.VideoPlayerActivity
import com.chrynan.video.ui.fragment.VideoDetailsFragment
import com.chrynan.video.ui.fragment.VideoPlayerFragment
import com.chrynan.video.utils.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class VideoPlayerActivityModule {

    @Binds
    @ActivityScope
    @ActivityContextQualifier
    abstract fun bindActivityContext(activity: VideoPlayerActivity): ActivityContext

    @FragmentScope
    @ContributesAndroidInjector(modules = [VideoPlayerFragmentModule::class])
    abstract fun videoFragmentInjector(): VideoPlayerFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [VideoDetailsFragmentModule::class])
    abstract fun videoDetailsInjector(): VideoDetailsFragment
}