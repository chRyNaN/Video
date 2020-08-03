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
import com.chrynan.video.player.AndroidMediaMetadataRetriever
import com.chrynan.video.player.AndroidPlaylistCreator
import com.chrynan.video.player.MediaMetadataRetriever
import com.chrynan.video.player.PlaylistCreator
import com.chrynan.video.player.converter.*
import com.chrynan.video.utils.ApplicationContext
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
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
            SimpleExoPlayer.Builder(context)
                .build()

        @Provides
        @JvmStatic
        @Singleton
        fun provideDataSourceFactory(@ApplicationContextQualifier context: ApplicationContext): DataSource.Factory =
            DefaultDataSourceFactory(context, Util.getUserAgent(context, "Video"))

        @Provides
        @JvmStatic
        @Singleton
        fun provideMediaSourcePlayableConverter(dataSourceFactory: DataSource.Factory): MediaSourcePlayableConverter =
            MediaSourcePlayableConverter(dataSourceFactory)

        @Provides
        @JvmStatic
        @Singleton
        fun provideMediaSourceEffectPlayableConverter(mediaSourceConverter: MediaSourcePlayableConverter): MediaSourceEffectPlayableConverter =
            MediaSourceEffectPlayableConverter(mediaSourceConverter)

        @Provides
        @JvmStatic
        @Singleton
        fun providePlaylistPlayableConverter(
            mediaSourceConverter: MediaSourcePlayableConverter,
            mediaSourceEffectConverter: MediaSourceEffectPlayableConverter
        ): PlaylistPlayableConverter =
            PlaylistPlayableConverter(mediaSourceConverter, mediaSourceEffectConverter)

        @Provides
        @JvmStatic
        @Singleton
        fun provideDelegatePlayableConverter(
            mediaSourceConverter: MediaSourcePlayableConverter,
            mediaSourceEffectConverter: MediaSourceEffectPlayableConverter,
            playlistConverter: PlaylistPlayableConverter
        ): DelegatePlayableConverter = DelegatePlayableConverter(
            converters = listOf(
                mediaSourceConverter,
                mediaSourceEffectConverter,
                playlistConverter
            )
        )

        @Provides
        @JvmStatic
        @Singleton
        fun providePlaylistCreator(converter: DelegatePlayableConverter): PlaylistCreator =
            AndroidPlaylistCreator(converter)

        @Provides
        @JvmStatic
        @Singleton
        fun provideMediaMetadataRetriever(@ApplicationContextQualifier context: ApplicationContext): MediaMetadataRetriever =
            AndroidMediaMetadataRetriever(context)

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
}