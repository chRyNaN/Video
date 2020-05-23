package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.ui.view.SearchView
import com.chrynan.video.viewmodel.SectionHeaderViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.common.model.VideoInfo
import com.chrynan.video.di.qualifier.SearchQualifier
import com.chrynan.video.ui.widget.*
import com.chrynan.video.viewmodel.ChannelListItemViewModel
import com.chrynan.video.viewmodel.VideoRecommendationViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment(),
    SearchView,
    VideoOptionsListener {

    companion object {

        fun newInstance() = SearchFragment()
    }

    @Inject
    @field:SearchQualifier.Adapter
    lateinit var resultAdapter: RecyclerViewAdapter

    @Inject
    @field:SearchQualifier.LayoutManager
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchWidget?.setBackgroundShape(BackgroundShape.Round)

        searchFilterItemChipGroup?.let { group ->
            val chipOne = chipOf(group, ChipStyle.FILTER, "One", ChipBackgroundColor.ACCENT_ONE)
            val chipTwo = chipOf(group, ChipStyle.FILTER, "Two", ChipBackgroundColor.ACCENT_TWO)
            val chipThree =
                chipOf(group, ChipStyle.FILTER, "Three", ChipBackgroundColor.ACCENT_THREE)

            group.addView(chipOne)
            group.addView(chipTwo)
            group.addView(chipThree)
        }

        val channel = ChannelListItemViewModel(
            channelId = "",
            providerUri = "",
            title = "Channel Title with Long Name",
            description = "Channel Description",
            channelImageUri = "",
            isSubscribed = false
        )

        searchResultsRecyclerView?.apply {
            layoutManager = linearLayoutManager
            adapter = resultAdapter

            resultAdapter.items = listOf(
                SectionHeaderViewModel(header = "Results"),
                VideoRecommendationViewModel(
                    title = "Test Title",
                    channelName = "Test Channel Name",
                    detailText = "Test Detail Text",
                    videoInfo = VideoInfo(
                        videoId = "",
                        channelId = "",
                        videoUri = "",
                        providerUri = ""
                    ),
                    videoLength = "1:00",
                    videoImageUrl = ""
                ),
                channel
            )
        }
    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {

    }
}