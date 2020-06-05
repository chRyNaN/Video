package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.model.result.FeedResultItem
import com.chrynan.common.repository.FeedItemRepository
import com.chrynan.logger.Logger
import com.chrynan.video.di.qualifier.HomeQualifier
import com.chrynan.video.mapper.video.VideoShowcaseMapper
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import com.chrynan.video.viewmodel.AdapterItem
import com.chrynan.video.viewmodel.VideoShowcaseViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomePresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val feedRepository: FeedItemRepository,
    private val mapper: VideoShowcaseMapper,
    @HomeQualifier.AdapterItemHandler private val adapterItemHandler: AdapterItemHandler<AdapterItem>
) : BasePresenter(dispatchers) {

    fun loadFeed() {
        feedRepository.openSubscription()
            .map { list ->
                list.filterIsInstance<FeedResultItem.Video>()
                    .map { mapper.map(it.videoResult) }
            }.onStart {
                val videoInfo = VideoInfo(
                    videoId = "VideoId",
                    channelId = "ChannelId",
                    providerUri = "ProviderUri",
                    videoUri = "VideoUri",
                    previewImageUri = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.UCUcOcot_h55wnZNadIzsAHaDr%26pid%3DApi&f=1"
                )

                val list = listOf(
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

                emit(list)
            }
            .filter { it.isNotEmpty() }
            .calculateAndDispatchDiff(adapterItemHandler)
            .catch { Logger.logError(throwable = it, message = "Error loading Feed.") }
            .launchIn(this)
    }
}