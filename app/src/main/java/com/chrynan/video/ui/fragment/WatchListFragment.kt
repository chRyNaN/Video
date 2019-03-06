package com.chrynan.video.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.video.R
import com.chrynan.video.model.VideoInfo
import com.chrynan.video.model.WatchListItemViewModel
import com.chrynan.video.ui.adapter.WatchListItemAdapter
import com.chrynan.video.ui.view.WatchListView
import kotlinx.android.synthetic.main.fragment_watch_list.*
import javax.inject.Inject

class WatchListFragment : BaseFragment(),
    WatchListView,
    WatchListItemAdapter.WatchListItemSelectedListener {

    companion object {

        fun newInstance() = WatchListFragment()
    }

    @Inject
    lateinit var managerAdapter: ManagerRecyclerViewAdapter<UniqueAdapterItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_watch_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        watchListRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = managerAdapter

            val videoInfo = VideoInfo(
                videoUri = Uri.parse(""),
                videoId = "",
                channelId = "",
                providerUri = Uri.parse("")
            )

            managerAdapter.items = listOf(
                WatchListItemViewModel(
                    videoInfo = videoInfo,
                    videoImageUri = Uri.parse(""),
                    title = "Test Title",
                    description = "Test Description",
                    secondaryDescription = "Secondary Description"
                )
            )
        }
    }

    override fun onWatchListItemSelected(videoInfo: VideoInfo) {

    }
}