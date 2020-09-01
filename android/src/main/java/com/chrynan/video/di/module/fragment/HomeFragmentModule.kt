package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.player.AndroidMediaController
import com.chrynan.video.player.converter.DelegatePlayableConverter
import com.chrynan.video.presentation.core.Navigator
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.navigator.HomeScreen
import com.chrynan.video.presentation.state.HomeIntent
import com.chrynan.video.presentation.state.HomeState
import com.chrynan.video.ui.adapter.video.VideoShowcaseAdapter
import com.chrynan.video.ui.fragment.HomeFragment
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class HomeFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideMediaController(
            exoPlayer: SimpleExoPlayer,
            converter: DelegatePlayableConverter
        ): com.chrynan.video.player.MediaController = AndroidMediaController(exoPlayer, converter)
    }

    @Binds
    @FragmentScope
    abstract fun bindHomeView(fragment: HomeFragment): View<HomeIntent, HomeState>

    @Binds
    @FragmentScope
    abstract fun bindHomeNavigator(fragment: HomeFragment): Navigator<HomeScreen>

    @Binds
    @FragmentScope
    abstract fun bindVideoShowcaseItemSelectedListener(fragment: HomeFragment): VideoShowcaseAdapter.VideoShowcaseItemSelectedListener
}