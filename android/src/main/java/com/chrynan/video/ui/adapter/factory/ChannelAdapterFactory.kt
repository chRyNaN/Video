package com.chrynan.video.ui.adapter.factory

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.channel.ChannelHeaderAdapter
import com.chrynan.video.ui.adapter.channel.ChannelInfoAdapter
import com.chrynan.video.ui.adapter.channel.ChannelProviderAdapter
import com.chrynan.video.ui.adapter.channel.ChannelVideoListAdapter
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.decorator.ChannelListDecorator
import com.chrynan.video.ui.adapter.position.AdapterPositionManager
import com.chrynan.video.ui.adapter.position.LinearLayoutPositionManager
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.utils.ActivityContext
import javax.inject.Inject

@FragmentScope
class ChannelAdapterFactory @Inject constructor(
    channelHeaderAdapter: ChannelHeaderAdapter,
    channelProviderAdapter: ChannelProviderAdapter,
    channelListAdapter: ChannelVideoListAdapter,
    channelAdapter: ChannelInfoAdapter,
    headerAdapter: SectionHeaderAdapter,
    videoAdapter: VideoRecommendationAdapter,
    @ActivityContextQualifier context: ActivityContext,
    override val coroutineDispatchers: CoroutineDispatchers
) : BaseAdapterFactory() {

    override val adapters: Set<BaseAdapter<*>> by lazy {
        setOf(
            channelHeaderAdapter,
            channelProviderAdapter,
            channelListAdapter,
            channelAdapter,
            headerAdapter,
            videoAdapter
        )
    }

    override val positionManager: AdapterPositionManager by lazy {
        LinearLayoutPositionManager(LinearLayoutManager(context))
    }

    override val decorators: List<RecyclerView.ItemDecoration> by lazy {
        listOf(ChannelListDecorator(context))
    }
}