package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.fragment.VideoPlayerFragment
import com.chrynan.video.ui.view.VideoPlayerView
import dagger.Binds
import dagger.Module

@Module
internal abstract class VideoPlayerFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindVideoPlayerView(fragment: VideoPlayerFragment): VideoPlayerView
}