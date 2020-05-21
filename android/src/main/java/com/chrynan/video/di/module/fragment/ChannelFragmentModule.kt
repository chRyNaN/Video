package com.chrynan.video.di.module.fragment

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.ui.view.ChannelView
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.ChannelInfoAdapter
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
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

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideLayoutManager(context: Context) = LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideAdapter(
            channelAdapter: ChannelInfoAdapter,
            headerAdapter: SectionHeaderAdapter,
            videoAdapter: VideoRecommendationAdapter,
            layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(
                channelAdapter,
                headerAdapter,
                videoAdapter
            ),
            layoutManager = layoutManager
        )
    }

    @Binds
    @FragmentScope
    abstract fun bindChannelView(fragment: ChannelFragment): ChannelView

    @Binds
    @FragmentScope
    abstract fun bindVideoOptionsListener(fragment: ChannelFragment): VideoOptionsListener
}