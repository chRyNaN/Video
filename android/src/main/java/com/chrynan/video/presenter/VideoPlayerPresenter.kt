package com.chrynan.video.presenter

import android.util.Log
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.model.api.VideoAction
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.video.R
import com.chrynan.video.media.MediaController
import com.chrynan.video.media.MediaSourceCreator
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import com.chrynan.video.ui.view.VideoPlayerView
import com.chrynan.video.viewmodel.*
import kotlinx.coroutines.flow.*

class VideoPlayerPresenter(
    dispatchers: CoroutineDispatchers,
    private val adapterHandler: AdapterItemHandler<AdapterItem>,
    private val mediaController: MediaController,
    private val mediaSourceCreator: MediaSourceCreator,
    private val view: VideoPlayerView
) : BasePresenter(dispatchers) {

    fun loadVideo() {
        val source = mediaSourceCreator.fromUri("https://www.w3schools.com/html/mov_bbb.mp4")

        view.attachPlayer(mediaController.player)
        mediaController.load(source)
        mediaController.resume()
    }

    fun loadExtras() {
        Log.w("ADAPTER", "loadExtras; isBound = $isBound; context = $coroutineContext")

        val videoInfo = VideoInfo(
            videoId = "VideoId",
            channelId = "ChannelId",
            providerUri = "ProviderUri",
            videoUri = "VideoUri"
        )

        val shareAction = VideoInfoActionViewModel(
            videoInfo = videoInfo,
            action = VideoAction.Share(),
            icon = R.drawable.ic_video_action_share
        )

        val castAction = VideoInfoActionViewModel(
            videoInfo = videoInfo,
            action = VideoAction.Cast,
            icon = R.drawable.ic_video_action_cast
        )

        val downloadAction = VideoInfoActionViewModel(
            videoInfo = videoInfo,
            action = VideoAction.Download(""),
            icon = R.drawable.ic_video_action_download
        )

        val ratingUpAction = VideoInfoActionViewModel(
            videoInfo = videoInfo,
            action = VideoAction.RatingUp(true),
            icon = R.drawable.ic_video_action_rating_up
        )

        val ratingDownAction = VideoInfoActionViewModel(
            videoInfo = videoInfo,
            action = VideoAction.RatingDown(false),
            icon = R.drawable.ic_video_action_rating_down
        )

        val flagAction = VideoInfoActionViewModel(
            videoInfo = videoInfo,
            action = VideoAction.Flag(false),
            icon = R.drawable.ic_video_action_flag
        )

        val items = listOf(
            VideoInfoHeaderViewModel(
                videoInfo = videoInfo,
                title = "A Really Cool Video",
                detail = "225k Published Today at 5:00pm",
                actions = listOf(
                    shareAction,
                    castAction,
                    downloadAction,
                    ratingUpAction,
                    ratingDownAction,
                    flagAction
                ),
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
                isSubscribedToChannel = true
            ),
            VideoInfoAboutViewModel(
                videoInfo = videoInfo,
                about = "Description"
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
                videoLength = "10:00"
            )
        )

        Log.w("ADAPTER", "items = $items")

        val job = flowOf(items)
            .onEach { Log.w("ADAPTER", "onEach: it = $it") }
            .flowOn(dispatchers.io)
            .calculateAndDispatchDiff(adapterHandler)
            .launchIn(this)

        Log.w("ADAPTER", "job = $job")
    }
}