package com.chrynan.video.ui.adapter.channel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.viewmodel.ChannelVideoListViewModel
import kotlinx.android.synthetic.main.adapter_channel_video_list_item.view.*
import javax.inject.Inject

@Adapter
class ChannelVideoListItemAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<ChannelVideoListViewModel.ChannelVideoListItemViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(ChannelVideoListItemAdapter::class.java)

    override fun onHandlesItem(item: Any) =
        item is ChannelVideoListViewModel.ChannelVideoListItemViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_channel_video_list_item, parent, false)

    override fun View.onBindItem(
        item: ChannelVideoListViewModel.ChannelVideoListItemViewModel,
        position: Int
    ) {
        Glide.with(this)
            .load(item.videoInfo.previewImageUri)
            .into(adapterChannelVideoListItemImageView)

        adapterChannelVideoListItemTitleTextView?.text = item.title
        adapterChannelVideoListItemDescriptionTextView?.text = item.description
    }
}