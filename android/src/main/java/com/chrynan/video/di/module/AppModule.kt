package com.chrynan.video.di.module

import com.chrynan.video.ui.VideoApplication
import com.chrynan.video.utils.AppContext
import dagger.Binds
import dagger.Module

@Module
internal abstract class AppModule {

    @Binds
    abstract fun bindAppContext(application: VideoApplication): AppContext
}