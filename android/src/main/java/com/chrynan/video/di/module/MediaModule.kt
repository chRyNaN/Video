package com.chrynan.video.di.module

import android.content.Context
import coil.ImageLoader
import com.chrynan.video.di.qualifier.OkHttpQualifier
import com.chrynan.video.media.AndroidMediaSourceCreator
import com.chrynan.video.media.MediaController
import com.chrynan.video.media.MediaSourceCreator
import com.chrynan.video.media.SimpleExoPlayerMediaController
import com.chrynan.video.utils.ApplicationContext
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
internal abstract class MediaModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun provideSimpleExoPlayer(context: ApplicationContext): SimpleExoPlayer =
            SimpleExoPlayer.Builder(context).build()

        @Provides
        @JvmStatic
        @Singleton
        fun provideImageLoader(
            context: ApplicationContext,
            @OkHttpQualifier.Coil coilOkHttpClient: OkHttpClient
        ) = ImageLoader.Builder(context)
            .crossfade(true)
            .okHttpClient { coilOkHttpClient }
            .build()
    }

    @Binds
    abstract fun bindMediaSourceCreator(creator: AndroidMediaSourceCreator): MediaSourceCreator

    @Binds
    abstract fun bindMediaController(controller: SimpleExoPlayerMediaController): MediaController
}