package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.Inject
import com.chrynan.video.R
import com.chrynan.video.model.ChannelInfoViewModel
import kotlinx.android.synthetic.main.adapter_channel_info.view.*

class ChannelInfoAdapter @Inject constructor() : AnotherAdapter<ChannelInfoViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is ChannelInfoViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_channel_info, parent, false)

    override fun onBindItem(view: View, item: ChannelInfoViewModel) {
        view.apply {
            channelTitleTextView?.text = item.name
            totalVideoViewCountTextView?.text = item.totalVideoViewCount
            subscriberCountTextView?.text = item.subscriberCount
            providerNameTextView?.text = item.providerServiceName
            createdTimeTextView?.text = item.created
            lastUpdatedTimeTextView?.text = item.lastUpdated
            aboutTextView?.text = item.about
        }
    }
}