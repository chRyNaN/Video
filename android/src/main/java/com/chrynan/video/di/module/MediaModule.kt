package com.chrynan.video.di.module

import android.os.Build.VERSION.SDK_INT
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.fetch.VideoFrameFileFetcher
import coil.fetch.VideoFrameUriFetcher
import com.chrynan.video.di.qualifier.ApplicationContextQualifier
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
        fun provideSimpleExoPlayer(@ApplicationContextQualifier context: ApplicationContext): SimpleExoPlayer =
            SimpleExoPlayer.Builder(context).build()

        @Provides
        @JvmStatic
        @Singleton
        fun provideImageLoader(
            @ApplicationContextQualifier context: ApplicationContext,
            @OkHttpQualifier.Coil coilOkHttpClient: OkHttpClient
        ) = ImageLoader.Builder(context)
            .crossfade(true)
            .okHttpClient { coilOkHttpClient }
            .componentRegistry {
                add(SvgDecoder(context))
            }
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder())
                } else {
                    add(GifDecoder())
                }
            }
            .componentRegistry {
                add(VideoFrameFileFetcher(context))
                add(VideoFrameUriFetcher(context))
            }
            .build()
    }

    @Binds
    abstract fun bindMediaSourceCreator(creator: AndroidMediaSourceCreator): MediaSourceCreator

    @Binds
    abstract fun bindMediaController(controller: SimpleExoPlayerMediaController): MediaController
}