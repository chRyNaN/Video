package com.chrynan.video.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.viewmodel.VideoInfoHeaderViewModel
import com.chrynan.video.R
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.ui.adapter.core.BaseNestedListAdapter
import com.chrynan.video.ui.adapter.factory.AdapterFactory
import com.chrynan.video.ui.adapter.factory.VideoActionAdapterFactory
import com.chrynan.video.utils.ActivityContext
import kotlinx.android.synthetic.main.adapter_video_info_header.view.*
import javax.inject.Inject

@Adapter
class VideoInfoHeaderAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    @ActivityContextQualifier private val context: ActivityContext,
    private val videoInfoProviderAdapter: VideoInfoProviderAdapter,
    private val videoInfoActionAdapter: VideoInfoActionAdapter
) : BaseNestedListAdapter<VideoInfoHeaderViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoInfoHeaderAdapter::class.java)

    override fun createNewAdapterFactory(): AdapterFactory =
        VideoActionAdapterFactory(
            context = context,
            coroutineDispatchers = dispatchers,
            videoInfoProviderAdapter = videoInfoProviderAdapter,
            videoInfoActionAdapter = videoInfoActionAdapter
        )

    override fun onHandlesItem(item: Any) = item is VideoInfoHeaderViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.adapter_video_info_header, parent, false)

    override fun View.onBindItem(item: VideoInfoHeaderViewModel, position: Int) {
        adapterVideoInfoHeaderTitleTextView?.text = item.title
        adapterVideoInfoHeaderDetailTextView?.text = item.detail

        bindNestedItems(adapterVideoInfoHeaderRecyclerView, item.actions)
    }
}