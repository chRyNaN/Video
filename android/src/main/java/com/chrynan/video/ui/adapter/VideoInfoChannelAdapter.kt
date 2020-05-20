package com.chrynan.video.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.presentation.viewmodel.VideoInfoChannelViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import kotlinx.android.synthetic.main.adapter_video_info_channel.view.*
import javax.inject.Inject

@Adapter
class VideoInfoChannelAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<VideoInfoChannelViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoInfoChannelAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoChannelViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_video_info_channel, parent, false)

    override fun View.onBindItem(item: VideoInfoChannelViewModel, position: Int) {
        channelImageView?.setImageURI(Uri.parse(item.channelImageUrl))
        channelNameTextView?.text = item.channelName
        channelSubscribeCountTextView?.text = item.channelSubscriberCount
        channelSubscribeButton?.setOnClickListener { }
    }
}