package com.chrynan.video.di.module.activity

import com.chrynan.presentation.view.TopMenuView
import com.chrynan.video.R
import com.chrynan.video.controller.MainController
import com.chrynan.video.controller.VideoPlayerController
import com.chrynan.video.controller.provider.MainTabsProvider
import com.chrynan.video.controller.provider.VideoPlayerTabsProvider
import com.chrynan.video.di.module.fragment.*
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.navigator.MainNavigator
import com.chrynan.video.ui.activity.MainActivity
import com.chrynan.video.ui.fragment.*
import com.chrynan.video.ui.widget.expandable.ExpandableContainerView
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainActivityModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideMainController(
            activity: MainActivity,
            tabsProvider: MainTabsProvider
        ): MainController =
            MainController(
                activity = activity,
                containerId = R.id.baseFragmentContainer,
                tabProvider = tabsProvider
            )

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideVideoPlayerController(
            activity: MainActivity,
            tabsProvider: VideoPlayerTabsProvider
        ): VideoPlayerController =
            VideoPlayerController(
                activity = activity,
                containerId = R.id.videoFragmentContainer,
                tabProvider = tabsProvider
            )
    }

    @Binds
    @ActivityScope
    abstract fun bindTopMenuView(activity: MainActivity): TopMenuView

    @Binds
    @ActivityScope
    abstract fun bindExpandableView(activity: MainActivity): ExpandableContainerView

    @Binds
    @ActivityScope
    abstract fun bindMainNavigator(activity: MainActivity): MainNavigator

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun homeFragmentInjector(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun searchFragmentInjector(): SearchFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [UserContentFragmentModule::class])
    abstract fun userContentInjector(): UserContentFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SettingsFragmentModule::class])
    abstract fun settingsFragmentInjector(): SettingsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [VideoPlayerFragmentModule::class])
    abstract fun videoFragmentInjector(): VideoPlayerFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [QueueFragmentModule::class])
    abstract fun queueFragmentInjector(): QueueFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [WatchListFragmentModule::class])
    abstract fun watchListFragmentInjector(): WatchListFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ChannelFragmentModule::class])
    abstract fun channelFragmentInjector(): ChannelFragment
}