package com.chrynan.video.di.module.fragment

import com.chrynan.video.ui.view.ChannelView
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.channel.*
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.ui.fragment.ChannelFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class ChannelFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindChannelView(fragment: ChannelFragment): ChannelView

    @Binds
    @FragmentScope
    abstract fun bindVideoRecommendationItemSelectedListener(fragment: ChannelFragment): VideoRecommendationAdapter.VideoRecommendationItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindSubscribeButtonSelectedListener(fragment: ChannelFragment): ChannelHeaderAdapter.SubscribeButtonSelectedListener
}