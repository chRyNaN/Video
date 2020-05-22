package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.ui.view.ChannelView
import com.chrynan.video.viewmodel.ChannelInfoViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.common.model.VideoInfo
import com.chrynan.video.di.qualifier.ChannelQualifier
import com.chrynan.video.ui.adapter.decorator.ChannelListDecorator
import com.chrynan.video.viewmodel.ChannelHeaderViewModel
import com.chrynan.video.viewmodel.ChannelProviderViewModel
import kotlinx.android.synthetic.main.fragment_channel.*
import javax.inject.Inject

class ChannelFragment : BaseFragment(),
    ChannelView,
    VideoOptionsListener {

    companion object {

        fun newInstance() = ChannelFragment()
    }

    @Inject
    @field:ChannelQualifier.Adapter
    lateinit var managerAdapter: RecyclerViewAdapter

    @Inject
    @field:ChannelQualifier.LayoutManager
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    @field:ChannelQualifier.Decorator
    lateinit var decorator: ChannelListDecorator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        layoutInflater.inflate(R.layout.fragment_channel, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        channelRecyclerView?.apply {
            layoutManager = linearLayoutManager
            adapter = managerAdapter
            addItemDecoration(decorator)

            val header = ChannelHeaderViewModel(
                name = "chRyNaN",
                subscriberCount = "1,000,000",
                videoCountString = "5,000,000",
                totalVideoViewCount = "5,000,000",
                channelId = "",
                providerUri = ""
            )

            val provider = ChannelProviderViewModel(
                providerServiceName = "chRyNaN Codes",
                channelId = "",
                providerUri = ""
            )

            managerAdapter.items = listOf(
                header,
                provider,
                ChannelInfoViewModel(
                    about = "A Channel you might like.",
                    created = "Today",
                    lastUpdated = "Today",
                    headerImageUri = "",
                    channelImageUri = "",
                    isSubscribed = false,
                    channelId = "",
                    providerUri = "",
                    channelUrl = ""
                )
            )
        }
    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {

    }
}