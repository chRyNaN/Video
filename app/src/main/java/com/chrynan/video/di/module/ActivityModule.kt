package com.chrynan.video.di.module

import com.chrynan.video.di.module.activity.MainActivityModule
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity
}