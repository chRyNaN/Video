package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.model.VideoAction
import com.chrynan.common.model.VideoInfo
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import com.chrynan.video.viewmodel.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn

class VideoPlayerPresenter(
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

        val shareAction = VideoAction.LocalAction(
            icon = R.drawable.ic_share,
            localType = VideoAction.LocalAction.LocalType.SHARE
        )

        val items = listOf(
            VideoInfoHeaderViewModel(
                videoInfo = videoInfo,
                title = "A Really Cool Video",
                detail = "225k Published Today at 5:00pm",
                actions = listOf(VideoInfoActionViewModel(videoInfo, shareAction)),
                provider = VideoInfoProviderViewModel(
                    videoInfo = videoInfo,
                    providerServiceName = "chRyNaN Video Provider"
                )
            ),
            VideoInfoChannelViewModel(
                videoInfo = videoInfo,
                channelName = "chRyNaN",
                channelImageUrl = null,
                channelSubscriberCount = "250k",
                showChannelSubscribeCount = true,
                isSubscribedToChannel = true
            ),
            VideoInfoDescriptionViewModel(
                videoInfo = videoInfo,
                description = "Description"
            ),
            VideoInfoDetailsViewModel(
                videoInfo = videoInfo,
                category = "Category",
                tags = listOf("TagOne", "TagTwo")
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