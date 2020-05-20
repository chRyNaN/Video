package com.chrynan.video.di.module

import android.content.Context
import com.chrynan.video.media.AndroidMediaSourceCreator
import com.chrynan.video.media.MediaController
import com.chrynan.video.media.MediaSourceCreator
import com.chrynan.video.media.SimpleExoPlayerMediaController
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class MediaModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideSimpleExoPlayer(context: Context): SimpleExoPlayer =
            SimpleExoPlayer.Builder(context).build()
    }

    @Binds
    abstract fun bindMediaSourceCreator(creator: AndroidMediaSourceCreator): MediaSourceCreator

    @Binds
    abstract fun bindMediaController(controller: SimpleExoPlayerMediaController): MediaController
}