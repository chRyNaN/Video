package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.ui.view.WatchListView
import com.chrynan.video.presentation.viewmodel.WatchListItemViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.WatchListItemAdapter
import com.chrynan.video.common.model.core.ID
import com.chrynan.video.common.model.core.UriString
import kotlinx.android.synthetic.main.fragment_watch_list.*

class WatchListFragment : Fragment(),
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

            managerAdapter.items = listOf(
                WatchListItemViewModel(
                    providerUri = "",
                    videoId = "",
                    videoImageUri = "",
                    title = "Test Title",
                    description = "Test Description",
                    secondaryDescription = "Secondary Description"
                )
            )
        }
    }

    override fun onWatchListItemSelected(providerUri: UriString, videoId: ID) {

    }
}