package com.chrynan.video.ui.adapter.channel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.viewmodel.ChannelHeaderViewModel
import kotlinx.android.synthetic.main.adapter_channel_header.view.*
import javax.inject.Inject

@Adapter
class ChannelHeaderAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<ChannelHeaderViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(ChannelHeaderAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is ChannelHeaderViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_channel_header, parent, false)

    override fun View.onBindItem(item: ChannelHeaderViewModel, position: Int) {
        adapterChannelHeaderTitleTextView?.text = item.name
        adapterChannelHeaderTotalVideoViewCountTextView?.text = item.totalVideoViewCount
        adapterChannelHeaderSubscriberCountTextView?.text = item.subscriberCount
    }
}