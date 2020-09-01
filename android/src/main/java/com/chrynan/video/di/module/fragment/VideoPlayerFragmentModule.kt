package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.player.AndroidMediaController
import com.chrynan.video.player.MediaController
import com.chrynan.video.player.converter.DelegatePlayableConverter
import com.chrynan.video.presentation.core.Navigator
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.navigator.VideoPlayerScreen
import com.chrynan.video.presentation.state.VideoPlayerIntent
import com.chrynan.video.presentation.state.VideoPlayerState
import com.chrynan.video.ui.fragment.VideoPlayerFragment
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class VideoPlayerFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideMediaController(
            exoPlayer: SimpleExoPlayer,
            converter: DelegatePlayableConverter
        ): MediaController = AndroidMediaController(exoPlayer, converter)
    }

    @Binds
    @FragmentScope
    abstract fun bindVideoPlayerView(fragment: VideoPlayerFragment): View<VideoPlayerIntent, VideoPlayerState>

    @Binds
    @FragmentScope
    abstract fun bindVideoPlayerNavigator(fragment: VideoPlayerFragment): Navigator<VideoPlayerScreen>
}