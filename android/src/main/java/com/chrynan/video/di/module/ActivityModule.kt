package com.chrynan.video.di.module

import com.chrynan.video.di.module.activity.ChannelActivityModule
import com.chrynan.video.di.module.activity.LauncherActivityModule
import com.chrynan.video.di.module.activity.MainActivityModule
import com.chrynan.video.di.module.activity.ServiceProviderActivityModule
import com.chrynan.video.di.module.activity.VideoPlayerActivityModule
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.ui.activity.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [LauncherActivityModule::class])
    abstract fun launcherActivityInjector(): LauncherActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ServiceProviderActivityModule::class])
    abstract fun serviceProviderActivityInjector(): ServiceProviderActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [VideoPlayerActivityModule::class])
    abstract fun videoPlayerActivityInjector(): VideoPlayerActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ChannelActivityModule::class])
    abstract fun channelActivityInjector(): ChannelActivity
}