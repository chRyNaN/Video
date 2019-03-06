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
import com.chrynan.video.model.VideoShowcaseViewModel
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.VideoShowcaseAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.video.ui.view.HomeView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(),
    HomeView,
    VideoOptionsListener {

    companion object {

        fun newInstance() = HomeFragment()
    }

    private val videoOptionsMenuBottomSheet by lazy { MenuBottomSheetDialogFragment.newInstance(menuResId = R.menu.menu_video_options) }

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

            val videoInfo = VideoInfo(
                videoId = "VideoId",
                channelId = "ChannelId",
                providerUri = Uri.parse("ProviderUri"),
                videoUri = Uri.parse("VideoUri")
            )

            managerAdapter.items = listOf(
                VideoShowcaseViewModel(
                    videoInfo = videoInfo,
                    title = "Another Video Title",
                    details = "Some Video Details Here",
                    provider = "chRyNaN",
                    videoLength = "5:30",
                    videoImageUrl = "",
                    channelImageUrl = ""
                ),
                VideoShowcaseViewModel(
                    videoInfo = videoInfo,
                    title = "Another Video Title",
                    details = "Some Video Details Here",
                    provider = "chRyNaN",
                    videoLength = "5:30",
                    videoImageUrl = "",
                    channelImageUrl = ""
                ),
                VideoShowcaseViewModel(
                    videoInfo = videoInfo,
                    title = "Another Video Title",
                    details = "Some Video Details Here",
                    provider = "chRyNaN",
                    videoLength = "5:30",
                    videoImageUrl = "",
                    channelImageUrl = ""
                ),
                VideoShowcaseViewModel(
                    videoInfo = videoInfo,
                    title = "Another Video Title",
                    details = "Some Video Details Here",
                    provider = "chRyNaN",
                    videoLength = "5:30",
                    videoImageUrl = "",
                    channelImageUrl = ""
                )
            )
        }
    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {
        videoOptionsMenuBottomSheet.show(childFragmentManager, null)
    }
}