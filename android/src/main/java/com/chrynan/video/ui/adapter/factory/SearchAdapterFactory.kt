package com.chrynan.video.ui.adapter.factory

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.channel.ChannelListItemAdapter
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.position.AdapterPositionManager
import com.chrynan.video.ui.adapter.position.LinearLayoutPositionManager
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.utils.ActivityContext
import javax.inject.Inject

@FragmentScope
class SearchAdapterFactory @Inject constructor(
    headerAdapter: SectionHeaderAdapter,
    videoRecommendationAdapter: VideoRecommendationAdapter,
    channelListItemAdapter: ChannelListItemAdapter,
    @ActivityContextQualifier context: ActivityContext,
    override val coroutineDispatchers: CoroutineDispatchers
) : BaseAdapterFactory() {

    override val adapters: Set<BaseAdapter<*>> by lazy {
        setOf(
            headerAdapter,
            videoRecommendationAdapter,
            channelListItemAdapter
        )
    }

    override val positionManager: AdapterPositionManager by lazy {
        LinearLayoutPositionManager(LinearLayoutManager(context))
    }
}