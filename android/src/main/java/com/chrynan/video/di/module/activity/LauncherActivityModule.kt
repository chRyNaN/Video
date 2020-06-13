package com.chrynan.video.di.module.activity

import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.navigator.LauncherNavigator
import com.chrynan.video.ui.activity.LauncherActivity
import dagger.Binds
import dagger.Module

@Module
internal abstract class LauncherActivityModule {

    @Binds
    @ActivityScope
    abstract fun bindLauncherNavigator(activity: LauncherActivity): LauncherNavigator
}