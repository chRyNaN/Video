package com.chrynan.video.ui.adapter.channel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.R
import com.chrynan.video.di.qualifier.ChannelVideoListQualifier
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import com.chrynan.video.viewmodel.AdapterItem
import com.chrynan.video.viewmodel.ChannelVideoListViewModel
import kotlinx.android.synthetic.main.adapter_channel_video_list.view.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@Adapter
class ChannelVideoListAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    @ChannelVideoListQualifier.Adapter private val videoAdapter: RecyclerViewAdapter,
    @ChannelVideoListQualifier.LayoutManager private val linearLayoutManager: LinearLayoutManager,
    @ChannelVideoListQualifier.AdapterItemHandler private val adapterItemHandler: AdapterItemHandler<AdapterItem>
) : BaseAdapter<ChannelVideoListViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(ChannelVideoListAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is ChannelVideoListViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_channel_video_list, parent, false)

    override fun View.onBindItem(item: ChannelVideoListViewModel, position: Int) {
        adapterChannelVideoListHeaderTextView?.text = item.listName

        adapterChannelVideoListRecyclerView?.apply {
            adapter = videoAdapter
            layoutManager = linearLayoutManager
        }

        flowOf(item.items)
            .calculateAndDispatchDiff(adapterItemHandler)
            .launchIn(this@ChannelVideoListAdapter)
    }
}