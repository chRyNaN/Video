package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.ui.view.HomeView
import com.chrynan.video.viewmodel.VideoShowcaseViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.video.di.qualifier.HomeQualifier
import com.chrynan.video.presenter.HomePresenter
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.decorator.HomeListDecorator
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(),
    HomeView,
    VideoOptionsListener {

    companion object {

        fun newInstance() = HomeFragment()
    }

    @Inject
    override lateinit var presenter: HomePresenter

    @Inject
    @field:HomeQualifier.Adapter
    lateinit var managerAdapter: RecyclerViewAdapter

    @Inject
    @field:HomeQualifier.LayoutManager
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    @field:HomeQualifier.Decorator
    lateinit var decorator: HomeListDecorator

    private val videoOptionsMenuBottomSheet by lazy {
        MenuBottomSheetDialogFragment.newInstance(
            menuResId = R.menu.menu_video_options
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.apply {

            layoutManager = linearLayoutManager
            adapter = managerAdapter
            addItemDecoration(decorator)

            val videoInfo = VideoInfo(
                videoId = "VideoId",
                channelId = "ChannelId",
                providerUri = "ProviderUri",
                videoUri = "VideoUri",
                previewImageUri = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.UCUcOcot_h55wnZNadIzsAHaDr%26pid%3DApi&f=1"
            )

            managerAdapter.items = listOf(
                VideoShowcaseViewModel(
                    videoInfo = videoInfo,
                    title = "Video Title One",
                    details = "Some Video Details Here",
                    provider = "chRyNaN",
                    videoLength = "5:30",
                    channelImageUrl = ""
                ),
                VideoShowcaseViewModel(
                    videoInfo = videoInfo,
                    title = "Video Title Two",
                    details = "Some Video Details Here",
                    provider = "chRyNaN",
                    videoLength = "5:30",
                    channelImageUrl = ""
                ),
                VideoShowcaseViewModel(
                    videoInfo = videoInfo,
                    title = "Video Title Three",
                    details = "Some Video Details Here",
                    provider = "chRyNaN",
                    videoLength = "5:30",
                    channelImageUrl = ""
                ),
                VideoShowcaseViewModel(
                    videoInfo = videoInfo,
                    title = "Video Title Four",
                    details = "Some Video Details Here",
                    provider = "chRyNaN",
                    videoLength = "5:30",
                    channelImageUrl = ""
                )
            )
        }
    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {
        videoOptionsMenuBottomSheet.show(childFragmentManager, null)
    }
}