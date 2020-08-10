package com.chrynan.video.ui.adapter.channel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.R
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.ui.adapter.core.*
import com.chrynan.video.ui.adapter.factory.AdapterFactory
import com.chrynan.video.ui.adapter.factory.ChannelVideoListAdapterFactory
import com.chrynan.video.utils.ActivityContext
import com.chrynan.video.presentation.viewmodel.ChannelVideoListViewModel
import kotlinx.android.synthetic.main.adapter_channel_video_list.view.*
import javax.inject.Inject

@Adapter
class ChannelVideoListAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listItemAdapter: ChannelVideoListItemAdapter,
    @ActivityContextQualifier private val context: ActivityContext
) : BaseNestedListAdapter<ChannelVideoListViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(ChannelVideoListAdapter::class.java)

    override fun createNewAdapterFactory(): AdapterFactory =
        ChannelVideoListAdapterFactory(
            coroutineDispatchers = dispatchers,
            listItemAdapter = listItemAdapter,
            context = context
        )

    override fun onHandlesItem(item: Any) = item is ChannelVideoListViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_channel_video_list, parent, false)

    override fun View.onBindItem(item: ChannelVideoListViewModel, position: Int) {
        adapterChannelVideoListHeaderTextView?.text = item.listName

        bindNestedItems(adapterChannelVideoListRecyclerView, item.items)
    }
}