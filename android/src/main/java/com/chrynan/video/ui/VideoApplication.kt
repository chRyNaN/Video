package com.chrynan.video.ui

import com.chrynan.strings.AndroidStringResourceAccessor
import com.chrynan.strings.Strings
import com.chrynan.video.di.component.DaggerAppComponent
import dagger.android.support.DaggerApplication

class VideoApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Strings.accessor = AndroidStringResourceAccessor(appContext = this)
    }

    override fun applicationInjector() =
        DaggerAppComponent.builder()
            .application(this)
            .build()
}