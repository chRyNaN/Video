package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString
import com.chrynan.logger.Logger
import com.chrynan.video.presentation.navigator.ChannelDetailsScreen
import com.chrynan.video.presentation.state.ChannelDetailsChange
import com.chrynan.video.presentation.state.ChannelDetailsIntent
import com.chrynan.video.presentation.state.ChannelDetailsState
import com.chrynan.video.presentation.presenter.ChannelDetailsPresenter
import com.chrynan.video.presentation.viewmodel.*
import com.chrynan.video.ui.adapter.channel.ChannelHeaderAdapter
import com.chrynan.video.ui.adapter.factory.ChannelAdapterFactory
import com.chrynan.video.ui.adapter.factory.bindAdapterFactory
import com.chrynan.video.ui.adapter.factory.calculateAndDispatchDiff
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class ChannelFragment : BaseFragment<ChannelDetailsIntent, ChannelDetailsState, ChannelDetailsChange, ChannelDetailsScreen>(),
    VideoRecommendationAdapter.VideoRecommendationItemSelectedListener,
    ChannelHeaderAdapter.SubscribeButtonSelectedListener {

    companion object {

        private const val KEY_PROVIDER_URI = "keyProviderUri"
        private const val KEY_CHANNEL_ID = "keyChannelId"

        fun newInstance(providerUri: UriString, channelId: ID) = ChannelFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_PROVIDER_URI, providerUri)
                putString(KEY_CHANNEL_ID, channelId)
            }
        }
    }

    @Inject
    override lateinit var presenter: ChannelDetailsPresenter

    @Inject
    lateinit var adapterFactory: ChannelAdapterFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(R.layout.fragment_channel, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        channelRecyclerView?.apply {
            bindAdapterFactory(adapterFactory)

            val videoInfo = VideoInfo(
                videoId = "",
                channelId = "",
                providerUri = "",
                videoUri = "",
                previewImageUri = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.UCUcOcot_h55wnZNadIzsAHaDr%26pid%3DApi&f=1"
            )

            val header =
                ChannelHeaderViewModel(
                    name = "chRyNaN",
                    subscriberCount = "1,000,000",
                    totalVideoViewCount = "5,000,000",
                    channelId = "",
                    providerUri = ""
                )

            val provider =
                ChannelProviderViewModel(
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

            val videoList =
                ChannelVideoListViewModel(
                    channelId = "",
                    providerUri = "",
                    listName = "List Name",
                    items = listOf(listItemOne, listItemTwo)
                )

            flowOf(
                listOf(
                    header,
                    SectionHeaderViewModel(
                        "About"
                    ),
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
            )
                .calculateAndDispatchDiff(adapterFactory)
                .catch { Logger.logError(throwable = it, message = "Error loading Channel Items.") }
                .launchIn(this@ChannelFragment)
        }
    }

    override fun intents(): Flow<ChannelDetailsIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: ChannelDetailsState) {
        TODO("Not yet implemented")
    }

    override fun goTo(screen: ChannelDetailsScreen) {
        TODO("Not yet implemented")
    }

    override fun onVideoRecommendationItemSelected(item: VideoRecommendationViewModel) {

    }

    override fun onVideoRecommendationOptionsSelected(item: VideoRecommendationViewModel) {
    }

    override fun onSubscribeButtonSelected(providerUri: UriString, channelId: ID) {

    }
}