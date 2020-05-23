package com.chrynan.video.ui.adapter.channel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.viewmodel.ChannelListItemViewModel
import kotlinx.android.synthetic.main.adapter_channel_list_item.view.*
import javax.inject.Inject

@Adapter
class ChannelListItemAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<ChannelListItemViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(ChannelListItemAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is ChannelListItemViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_channel_list_item, parent, false)

    override fun View.onBindItem(item: ChannelListItemViewModel, position: Int) {
        adapterChannelListItemCircleImageView?.load(item.channelImageUri) {
            placeholder(R.drawable.ic_default_user)
            error(R.drawable.ic_default_user)
        }
        adapterChannelListItemTitleTextView?.text = item.title
        adapterChannelListItemDescriptionTextView?.text = item.description
        adapterChannelListItemSubscribeButton?.isChecked = item.isSubscribed
        adapterChannelListItemSubscribeButton?.setOnCheckedChangeListener { _, isChecked ->
            // TODO
        }

        setOnClickListener {
            // TODO
        }
    }
}