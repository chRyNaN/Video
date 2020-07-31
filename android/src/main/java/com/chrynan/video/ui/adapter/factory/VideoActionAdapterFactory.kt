package com.chrynan.video.ui.adapter.factory

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.position.AdapterPositionManager
import com.chrynan.video.ui.adapter.position.LinearLayoutPositionManager
import com.chrynan.video.ui.adapter.video.VideoInfoActionAdapter
import com.chrynan.video.ui.adapter.video.VideoInfoProviderAdapter
import com.chrynan.video.utils.ActivityContext
import javax.inject.Inject

@FragmentScope
class VideoActionAdapterFactory @Inject constructor(
    @ActivityContextQualifier context: ActivityContext,
    videoInfoProviderAdapter: VideoInfoProviderAdapter,
    videoInfoActionAdapter: VideoInfoActionAdapter,
    override val coroutineDispatchers: CoroutineDispatchers
) : BaseAdapterFactory() {

    override val adapters: Set<BaseAdapter<*>> by lazy {
        setOf(
            videoInfoProviderAdapter,
            videoInfoActionAdapter
        )
    }

    override val positionManager: AdapterPositionManager by lazy {
        LinearLayoutPositionManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
    }
}