package com.chrynan.video.di.module.activity

import com.chrynan.video.coroutine.ActivityCoroutineScope
import com.chrynan.video.ui.view.TopMenuView
import com.chrynan.video.di.module.fragment.*
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.presentation.core.Navigator
import com.chrynan.video.presentation.navigator.MainScreen
import com.chrynan.video.ui.activity.MainActivity
import com.chrynan.video.ui.fragment.*
import com.chrynan.video.utils.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainActivityModule {

    @Binds
    @ActivityScope
    @ActivityContextQualifier
    abstract fun bindActivityContext(activity: MainActivity): ActivityContext

    @Binds
    @ActivityScope
    abstract fun bindActivityCoroutineScope(activity: MainActivity): ActivityCoroutineScope

    @Binds
    @ActivityScope
    abstract fun bindTopMenuView(activity: MainActivity): TopMenuView

    @Binds
    @ActivityScope
    abstract fun bindMainActivityNavigator(activity: MainActivity): Navigator<MainScreen>

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
    @ContributesAndroidInjector(modules = [QueueFragmentModule::class])
    abstract fun queueFragmentInjector(): QueueFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [WatchListFragmentModule::class])
    abstract fun watchListFragmentInjector(): WatchListFragment
}