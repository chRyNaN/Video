package com.chrynan.video.di.module

import android.content.Context
import coil.util.CoilUtils
import com.chrynan.video.di.qualifier.OkHttpQualifier
import com.chrynan.video.utils.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
internal abstract class WebModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        @OkHttpQualifier.Coil
        fun provideCoilOkHttpClient(context: ApplicationContext) =
            OkHttpClient.Builder()
                .cache(CoilUtils.createDefaultCache(context))
                .build()
    }
}