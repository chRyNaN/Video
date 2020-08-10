package com.chrynan.video.ui.adapter.channel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.viewmodel.ChannelInfoViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import io.noties.markwon.Markwon
import kotlinx.android.synthetic.main.adapter_channel_info.view.*
import javax.inject.Inject

@Adapter
class ChannelInfoAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val markdownParser: Markwon
) : BaseAdapter<ChannelInfoViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(ChannelInfoAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is ChannelInfoViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_channel_info, parent, false)

    override fun View.onBindItem(item: ChannelInfoViewModel, position: Int) {
        adapterChannelInfoCreatedTimeTextView?.text = item.created
        adapterChannelInfoLastUpdatedTimeTextView?.text = item.lastUpdated

        if (item.about != null) {
            adapterChannelInfoAboutTextView?.let {
                it.visibility = View.VISIBLE
                markdownParser.setMarkdown(it, item.about)
            }
        } else {
            adapterChannelInfoAboutTextView?.visibility = View.GONE
        }
    }
}