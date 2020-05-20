package com.chrynan.presentation.presenter

import com.chrynan.common.Inject
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.presentation.adapter.core.AdapterItemHandler
import com.chrynan.presentation.adapter.core.calculateAndDispatchDiff
import com.chrynan.presentation.viewmodel.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn

class VideoPlayerPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val adapterHandler: AdapterItemHandler<AdapterItem>
) : BasePresenter(dispatchers) {

    fun loadVideo() {
        val videoInfo = VideoInfo(
            videoId = "VideoId",
            channelId = "ChannelId",
            providerUri = "ProviderUri",
            videoUri = "VideoUri"
        )

        val items = listOf(
            VideoInfoHeaderViewModel(
                videoInfo = videoInfo,
                title = "A Really Cool Video",
                viewCount = "225k",
                supportsRating = true,
                likeButtonText = "Like",
                dislikeButtonText = "Dislike",
                shareButtonText = "Share",
                isLiked = false,
                isDisliked = false
            ),
            VideoInfoChannelViewModel(
                videoInfo = videoInfo,
                channelName = "chRyNaN",
                channelImageUrl = "",
                channelSubscriberCount = "250k",
                showChannelSubscribeCount = true,
                isSubscribedToChannel = true
            ),
            VideoInfoProviderViewModel(
                videoInfo = videoInfo,
                providerServiceName = "chRyNaN Video Provider"
            ),
            VideoInfoDetailsViewModel(
                videoInfo = videoInfo,
                publishedDate = "Today",
                category = "Category",
                showCategory = true,
                tags = listOf("TagOne", "TagTwo")
            ),
            VideoInfoDescriptionViewModel(
                videoInfo = videoInfo,
                description = "Description"
            ),
            SectionHeaderViewModel(header = "Recommended Videos"),
            VideoRecommendationViewModel(
                title = "A Really Cool Video",
                channelName = "chRyNaN Codes",
                detailText = "Provided by chRyNaN",
                videoInfo = videoInfo,
                videoImageUrl = "",
                videoLength = "10:00"
            )
        )

        flowOf(items)
            .calculateAndDispatchDiff(adapterHandler)
            .launchIn(this)
    }

    fun loadExtras() {

    }
}