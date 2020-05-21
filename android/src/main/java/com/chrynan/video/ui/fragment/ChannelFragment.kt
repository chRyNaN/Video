package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.ui.view.ChannelView
import com.chrynan.video.viewmodel.ChannelInfoViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.viewmodel.VideoInfo
import kotlinx.android.synthetic.main.fragment_channel.*
import javax.inject.Inject

class ChannelFragment : BaseFragment(),
    ChannelView,
    VideoOptionsListener {

    companion object {

        fun newInstance() = ChannelFragment()
    }

    @Inject
    lateinit var managerAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        layoutInflater.inflate(R.layout.fragment_channel, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        channelRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = managerAdapter

            managerAdapter.items = listOf(
                ChannelInfoViewModel(
                    name = "chRyNaN",
                    about = "A Channel you might like.",
                    created = "Today",
                    lastUpdated = "Today",
                    subscriberCount = "1,000,000",
                    videoCountString = "5,000,000",
                    totalVideoViewCount = "5,000,000",
                    showSubscriberCount = true,
                    showTotalVideoViewCount = true,
                    showVideoCount = true,
                    headerImageUri = "",
                    channelImageUri = "",
                    isSubscribed = false,
                    providerServiceName = "chRyNaN Codes",
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