package com.chrynan.video.ui

import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.chrynan.video.coroutine.ApplicationCoroutineScope
import com.chrynan.video.di.component.DaggerAppComponent
import dagger.android.support.DaggerApplication
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class VideoApplication : DaggerApplication(),
    ApplicationCoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()

        Coil.setImageLoader(imageLoader)
    }

    override fun applicationInjector() =
        DaggerAppComponent.builder()
            .application(this)
            .build()
}