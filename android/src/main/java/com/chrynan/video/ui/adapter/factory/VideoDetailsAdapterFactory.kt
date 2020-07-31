package com.chrynan.video.ui.adapter.factory

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.decorator.VideoPlayerListDecorator
import com.chrynan.video.ui.adapter.video.*
import com.chrynan.video.utils.ActivityContext
import javax.inject.Inject

@FragmentScope
class VideoDetailsAdapterFactory @Inject constructor(
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

    override val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(context) }

    override val decorators: List<RecyclerView.ItemDecoration> by lazy {
        listOf(VideoPlayerListDecorator(context))
    }
}