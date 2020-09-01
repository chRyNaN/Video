package com.chrynan.video.di.module.activity

import com.chrynan.video.coroutine.ActivityCoroutineScope
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.presentation.core.Navigator
import com.chrynan.video.presentation.navigator.DeepLinkScreen
import com.chrynan.video.ui.activity.DeepLinkActivity
import com.chrynan.video.utils.ActivityContext
import dagger.Binds
import dagger.Module

@Module
internal abstract class DeepLinkActivityModule {

    @Binds
    @ActivityScope
    @ActivityContextQualifier
    abstract fun bindActivityContext(activity: DeepLinkActivity): ActivityContext

    @Binds
    @ActivityScope
    abstract fun bindActivityCoroutineScope(activity: DeepLinkActivity): ActivityCoroutineScope

    @Binds
    @ActivityScope
    abstract fun bindDeepLinkActivityNavigator(activity: DeepLinkActivity): Navigator<DeepLinkScreen>
}