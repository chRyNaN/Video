package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.presentation.core.Navigator
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.navigator.ChannelDetailsScreen
import com.chrynan.video.presentation.state.ChannelDetailsIntent
import com.chrynan.video.presentation.state.ChannelDetailsState
import com.chrynan.video.ui.adapter.channel.*
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.ui.fragment.ChannelFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class ChannelFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindChannelView(fragment: ChannelFragment): View<ChannelDetailsIntent, ChannelDetailsState>

    @Binds
    @FragmentScope
    abstract fun bindChannelNavigator(fragment: ChannelFragment): Navigator<ChannelDetailsScreen>

    @Binds
    @FragmentScope
    abstract fun bindVideoRecommendationItemSelectedListener(fragment: ChannelFragment): VideoRecommendationAdapter.VideoRecommendationItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindSubscribeButtonSelectedListener(fragment: ChannelFragment): ChannelHeaderAdapter.SubscribeButtonSelectedListener
}