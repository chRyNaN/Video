package com.chrynan.video.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.presentation.view.WatchListView
import com.chrynan.presentation.viewmodel.UniqueListItem
import com.chrynan.presentation.viewmodel.VideoInfo
import com.chrynan.presentation.viewmodel.WatchListItemViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.WatchListItemAdapter
import com.chrynan.video.utils.asUri
import kotlinx.android.synthetic.main.fragment_watch_list.*

class WatchListFragment : BaseFragment(),
    WatchListView,
    WatchListItemAdapter.WatchListItemSelectedListener {

    companion object {

        fun newInstance() = WatchListFragment()
    }

    lateinit var managerAdapter: RecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_watch_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        watchListRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = managerAdapter

            val videoInfo = VideoInfo(
                videoUri = Uri.parse("").asUri(),
                videoId = "",
                channelId = "",
                providerUri = Uri.parse("").asUri()
            )

            managerAdapter.items = listOf(
                WatchListItemViewModel(
                    videoInfo = videoInfo,
                    videoImageUri = Uri.parse("").asUri(),
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