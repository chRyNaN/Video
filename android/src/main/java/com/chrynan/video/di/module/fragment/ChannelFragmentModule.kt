package com.chrynan.video.di.module.fragment

import com.chrynan.presentation.view.ChannelView
import com.chrynan.video.ui.adapter.ChannelInfoAdapter
import com.chrynan.video.ui.adapter.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.VideoRecommendationAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.fragment.ChannelFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class ChannelFragmentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideAdapter(
            channelAdapter: ChannelInfoAdapter,
            headerAdapter: SectionHeaderAdapter,
            videoAdapter: VideoRecommendationAdapter
        ) = RecyclerViewAdapter(adapters = setOf(channelAdapter, headerAdapter, videoAdapter))
    }

    @Binds
    abstract fun bindChannelView(fragment: ChannelFragment): ChannelView

    @Binds
    abstract fun bindVideoOptionsListener(fragment: ChannelFragment): VideoOptionsListener
}