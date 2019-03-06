package com.chrynan.video.di.module.fragment

import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.video.ui.adapter.ChannelInfoAdapter
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.VideoRecommendationAdapter
import com.chrynan.video.ui.fragment.ChannelFragment
import com.chrynan.video.ui.view.ChannelView
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
        ) =
            ManagerRecyclerViewAdapter<UniqueAdapterItem>(adapters = setOf(channelAdapter, headerAdapter, videoAdapter))
    }

    @Binds
    abstract fun bindChannelView(fragment: ChannelFragment): ChannelView
}