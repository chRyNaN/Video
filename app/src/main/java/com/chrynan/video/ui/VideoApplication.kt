package com.chrynan.video.ui

import com.chrynan.video.di.component.DaggerAppComponent
import dagger.android.support.DaggerApplication

class VideoApplication : DaggerApplication() {

    override fun applicationInjector() =
        DaggerAppComponent.builder()
            .application(this)
            .build()
}