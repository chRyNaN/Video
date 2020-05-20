package com.chrynan.video.di.module

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.coroutine.AndroidCoroutineDispatchers
import com.chrynan.video.ui.VideoApplication
import com.chrynan.video.utils.AppContext
import dagger.Binds
import dagger.Module

@Module
internal abstract class AppModule {

    @Binds
    abstract fun bindAppContext(application: VideoApplication): AppContext

    @Binds
    abstract fun bindCoroutineDispatchers(dispatchers: AndroidCoroutineDispatchers): CoroutineDispatchers
}