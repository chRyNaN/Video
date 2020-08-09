package com.chrynan.video.di.module.activity

import com.chrynan.video.coroutine.ActivityCoroutineScope
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.presentation.navigator.LauncherScreen
import com.chrynan.video.presentation.navigator.Navigator
import com.chrynan.video.ui.activity.LauncherActivity
import com.chrynan.video.utils.ActivityContext
import dagger.Binds
import dagger.Module

@Module
internal abstract class LauncherActivityModule {

    @Binds
    @ActivityScope
    @ActivityContextQualifier
    abstract fun bindActivityContext(activity: LauncherActivity): ActivityContext

    @Binds
    @ActivityScope
    abstract fun bindActivityCoroutineScope(activity: LauncherActivity): ActivityCoroutineScope

    @Binds
    @ActivityScope
    abstract fun bindLauncherActivityNavigator(activity: LauncherActivity): Navigator<LauncherScreen>
}