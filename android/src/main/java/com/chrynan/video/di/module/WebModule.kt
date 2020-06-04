package com.chrynan.video.di.module

import coil.util.CoilUtils
import com.chrynan.video.di.qualifier.ApplicationContextQualifier
import com.chrynan.video.di.qualifier.OkHttpQualifier
import com.chrynan.video.utils.ApplicationContext
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
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
        fun provideCoilOkHttpClient(@ApplicationContextQualifier context: ApplicationContext) =
            OkHttpClient.Builder()
                .cache(CoilUtils.createDefaultCache(context))
                .build()

        @JvmStatic
        @Provides
        @Singleton
        fun provideKtorHttpClient(): HttpClient = HttpClient(io.ktor.client.engine.okhttp.OkHttp) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
        }
    }
}