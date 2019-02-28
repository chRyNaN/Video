package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.video.R
import com.chrynan.video.model.VideoShowcaseViewModel
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.VideoShowcaseAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.view.HomeView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(),
    HomeView,
    VideoOptionsListener {

    companion object {

        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView?.apply {
            val managerAdapter =
                ManagerRecyclerViewAdapter<UniqueAdapterItem>(
                    adapters = setOf(
                        SectionHeaderAdapter(),
                        VideoShowcaseAdapter(this@HomeFragment)
                    )
                )

            layoutManager = LinearLayoutManager(context)
            adapter = managerAdapter

            managerAdapter.items = listOf(
                VideoShowcaseViewModel(
                    videoId = "videoId",
                    channelId = "channelId",
                    providerUrl = "providerUrl",
                    title = "Another Video Title",
                    details = "Some Video Details Here",
                    provider = "chRyNaN",
                    videoLength = "5:30",
                    videoImageUrl = ""
                ),
                VideoShowcaseViewModel(
                    videoId = "videoId",
                    channelId = "channelId",
                    providerUrl = "providerUrl",
                    title = "Another Video Title",
                    details = "Some Video Details Here",
                    provider = "chRyNaN",
                    videoLength = "5:30",
                    videoImageUrl = ""
                )
            )
        }
    }

    override fun dismissSelected(videoId: String, channelId: String, providerUrl: String) {
    }

    override fun shareSelected(videoId: String, channelId: String, providerUrl: String) {
    }

    override fun reportSelected(videoId: String, channelId: String, providerUrl: String) {
    }

    override fun playNowSelected(videoId: String, channelId: String, providerUrl: String) {
    }

    override fun playNextSelected(videoId: String, channelId: String, providerUrl: String) {
    }

    override fun addToQueueSelected(videoId: String, channelId: String, providerUrl: String) {
    }
}