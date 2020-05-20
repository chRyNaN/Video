package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.presentation.view.WatchListView
import com.chrynan.presentation.viewmodel.VideoInfo
import com.chrynan.presentation.viewmodel.WatchListItemViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.WatchListItemAdapter
import kotlinx.android.synthetic.main.fragment_watch_list.*

class WatchListFragment : BaseFragment(),
    WatchListView,
    WatchListItemAdapter.WatchListItemSelectedListener {

    companion object {

        fun newInstance() = WatchListFragment()
    }

    lateinit var managerAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_watch_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        watchListRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = managerAdapter

            val videoInfo = VideoInfo(
                videoUri = "",
                videoId = "",
                channelId = "",
                providerUri = ""
            )

            managerAdapter.items = listOf(
                WatchListItemViewModel(
                    videoInfo = videoInfo,
                    videoImageUri = "",
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