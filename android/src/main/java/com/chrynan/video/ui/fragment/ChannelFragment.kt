package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.chrynan.video.ui.view.ChannelView
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString
import com.chrynan.video.di.qualifier.ChannelQualifier
import com.chrynan.video.presenter.ChannelPresenter
import com.chrynan.video.ui.adapter.channel.ChannelHeaderAdapter
import com.chrynan.video.ui.adapter.decorator.ChannelListDecorator
import com.chrynan.video.viewmodel.*
import kotlinx.android.synthetic.main.fragment_channel.*
import javax.inject.Inject

class ChannelFragment : BaseFragment(),
    ChannelView,
    VideoOptionsListener,
    ChannelHeaderAdapter.SubscribeButtonSelectedListener {

    companion object {

        fun newInstance() = ChannelFragment()
    }

    @Inject
    override lateinit var presenter: ChannelPresenter

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
    ): View? = layoutInflater.inflate(R.layout.fragment_channel, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        channelRecyclerView?.apply {
            layoutManager = linearLayoutManager
            adapter = managerAdapter
            addItemDecoration(decorator)

            val videoInfo = VideoInfo(
                videoId = "",
                channelId = "",
                providerUri = "",
                videoUri = "",
                previewImageUri = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.UCUcOcot_h55wnZNadIzsAHaDr%26pid%3DApi&f=1"
            )

            val header = ChannelHeaderViewModel(
                name = "chRyNaN",
                subscriberCount = "1,000,000",
                totalVideoViewCount = "5,000,000",
                channelId = "",
                providerUri = ""
            )

            val provider = ChannelProviderViewModel(
                providerServiceName = "chRyNaN Codes",
                channelId = "",
                providerUri = ""
            )

            val listItemOne = ChannelVideoListViewModel.ChannelVideoListItemViewModel(
                videoInfo = videoInfo,
                title = "Title One",
                description = "Description One"
            )

            val listItemTwo = ChannelVideoListViewModel.ChannelVideoListItemViewModel(
                videoInfo = videoInfo,
                title = "Title Two",
                description = "Description Two"
            )

            val videoList = ChannelVideoListViewModel(
                channelId = "",
                providerUri = "",
                listName = "List Name",
                items = listOf(listItemOne, listItemTwo)
            )

            managerAdapter.items = listOf(
                header,
                SectionHeaderViewModel("About"),
                ChannelInfoViewModel(
                    about = "A **Channel** *you* might like.\n\n ![Image](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.UCUcOcot_h55wnZNadIzsAHaDr%26pid%3DApi&f=1)",
                    created = "Today",
                    lastUpdated = "Today",
                    headerImageUri = "",
                    channelImageUri = "",
                    isSubscribed = false,
                    channelId = "",
                    providerUri = "",
                    channelUrl = ""
                ),
                provider,
                videoList
            )
        }
    }

    override fun showTitle(title: String) {
        channelCollapsingToolbarLayout?.title = title
    }

    override fun showBannerImage(imageUri: UriString) {
        channelBannerImageView?.load(imageUri)
    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {

    }

    override fun onSubscribeButtonSelected(providerUri: UriString, channelId: ID) {

    }
}