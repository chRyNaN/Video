package com.chrynan.video.ui

import coil.Coil
import coil.ImageLoader
import com.chrynan.logger.AndroidLogger
import com.chrynan.logger.Logger
import com.chrynan.video.BuildConfig
import com.chrynan.video.coroutine.ApplicationCoroutineScope
import com.chrynan.video.di.component.DaggerAppComponent
import com.chrynan.video.log.CrashReportLogger
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

    @Inject
    lateinit var crashReportLogger: CrashReportLogger

    override fun onCreate() {
        super.onCreate()

        Coil.setImageLoader(imageLoader)

        setupLogging()
    }

    override fun applicationInjector() =
        DaggerAppComponent.builder()
            .application(this)
            .build()

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Logger.loggable = AndroidLogger
        } else {
            Logger.loggable = crashReportLogger
        }
    }
}