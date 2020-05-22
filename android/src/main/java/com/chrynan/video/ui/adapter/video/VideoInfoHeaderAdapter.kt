package com.chrynan.video.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.viewmodel.VideoInfoHeaderViewModel
import com.chrynan.video.R
import com.chrynan.video.di.qualifier.VideoActionQualifier
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import com.chrynan.video.viewmodel.AdapterItem
import kotlinx.android.synthetic.main.adapter_video_info_header.view.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@Adapter
class VideoInfoHeaderAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    @VideoActionQualifier.Adapter private val actionAdapter: RecyclerViewAdapter,
    @VideoActionQualifier.LayoutManager private val linearLayoutManager: LinearLayoutManager,
    @VideoActionQualifier.AdapterItemHandler private val adapterItemHandler: AdapterItemHandler<AdapterItem>
) : BaseAdapter<VideoInfoHeaderViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoInfoHeaderAdapter::class.java)

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

        adapterVideoInfoHeaderRecyclerView?.apply {
            adapter = actionAdapter
            layoutManager = linearLayoutManager
        }

        flowOf(listOf(item.provider) + item.actions)
            .calculateAndDispatchDiff(adapterItemHandler)
            .launchIn(this@VideoInfoHeaderAdapter)
    }
}