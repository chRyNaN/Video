package com.chrynan.video.ui.adapter.factory

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.decorator.VideoPlayerListDecorator
import com.chrynan.video.ui.adapter.position.AdapterPositionManager
import com.chrynan.video.ui.adapter.position.LinearLayoutPositionManager
import com.chrynan.video.ui.adapter.video.*
import com.chrynan.video.utils.ActivityContext
import javax.inject.Inject

@FragmentScope
class OpenVideoDetailsAdapterFactory @Inject constructor(
    @ActivityContextQualifier context: ActivityContext,
    videoInfoHeaderAdapter: VideoInfoHeaderAdapter,
    videoInfoChannelAdapter: VideoInfoChannelAdapter,
    videoInfoDescriptionAdapter: VideoInfoAboutAdapter,
    videoInfoDetailsAdapter: VideoInfoDetailsAdapter,
    sectionHeaderAdapter: SectionHeaderAdapter,
    videoRecommendationAdapter: VideoRecommendationAdapter,
    videoShowcaseAdapter: VideoShowcaseAdapter,
    override val coroutineDispatchers: CoroutineDispatchers
) : BaseAdapterFactory() {

    override val adapters: Set<BaseAdapter<*>> by lazy {
        setOf(
            videoInfoHeaderAdapter,
            videoInfoChannelAdapter,
            videoInfoDescriptionAdapter,
            videoInfoDetailsAdapter,
            sectionHeaderAdapter,
            videoRecommendationAdapter,
            videoShowcaseAdapter
        )
    }

    override val positionManager: AdapterPositionManager by lazy {
        LinearLayoutPositionManager(LinearLayoutManager(context))
    }

    override val decorators: List<RecyclerView.ItemDecoration> by lazy {
        listOf(VideoPlayerListDecorator(context))
    }
}