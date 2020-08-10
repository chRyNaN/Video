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
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.presentation.viewmodel.ChannelProviderViewModel
import kotlinx.android.synthetic.main.adapter_channel_provider.view.*
import javax.inject.Inject

@Adapter
class ChannelProviderAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<ChannelProviderViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(ChannelProviderAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is ChannelProviderViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_channel_provider, parent, false)

    override fun View.onBindItem(item: ChannelProviderViewModel, position: Int) {
        adapterChannelProviderNameTextView?.text = item.providerServiceName
    }
}