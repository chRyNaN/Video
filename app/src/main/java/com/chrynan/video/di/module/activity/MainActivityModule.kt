package com.chrynan.video.di.module.activity

import com.chrynan.video.R
import com.chrynan.video.controller.BaseActivityController
import com.chrynan.video.controller.Controller
import com.chrynan.video.controller.provider.MainTabsProvider
import com.chrynan.video.controller.tab.MainTabs
import com.chrynan.video.di.module.fragment.HomeFragmentModule
import com.chrynan.video.di.module.fragment.SearchFragmentModule
import com.chrynan.video.di.module.fragment.SettingsFragmentModule
import com.chrynan.video.di.module.fragment.VideoFragmentModule
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.navigator.MainNavigator
import com.chrynan.video.ui.activity.MainActivity
import com.chrynan.video.ui.fragment.HomeFragment
import com.chrynan.video.ui.fragment.SearchFragment
import com.chrynan.video.ui.fragment.SettingsFragment
import com.chrynan.video.ui.fragment.VideoFragment
import com.chrynan.video.ui.widget.expandable.ExpandableContainerView
import com.chrynan.video.ui.view.TopMenuView
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
        ): Controller<MainTabs> =
            BaseActivityController(
                activity = activity,
                containerId = R.id.baseFragmentContainer,
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
    @ContributesAndroidInjector(modules = [SettingsFragmentModule::class])
    abstract fun settingsFragmentInjector(): SettingsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [VideoFragmentModule::class])
    abstract fun videoFragmentInjector(): VideoFragment
}