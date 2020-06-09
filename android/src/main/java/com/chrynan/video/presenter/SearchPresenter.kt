package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.repository.SearchItemRepository
import com.chrynan.common.repository.TagSuggestionRepository
import com.chrynan.common.utils.mapEachItemWith
import com.chrynan.logger.Logger
import com.chrynan.video.di.qualifier.SearchQualifier
import com.chrynan.video.mapper.SearchResultMapper
import com.chrynan.video.mapper.TagItemMapper
import com.chrynan.video.viewmodel.TagItemViewModel
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import com.chrynan.video.ui.view.SearchView
import com.chrynan.video.viewmodel.AdapterItem
import com.chrynan.video.viewmodel.ChannelListItemViewModel
import com.chrynan.video.viewmodel.SectionHeaderViewModel
import com.chrynan.video.viewmodel.VideoRecommendationViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val view: SearchView,
    @SearchQualifier.AdapterItemHandler private val adapterItemHandler: AdapterItemHandler<AdapterItem>,
    private val searchItemRepository: SearchItemRepository,
    private val tagSuggestionRepository: TagSuggestionRepository,
    private val searchItemMapper: SearchResultMapper,
    private val tagItemMapper: TagItemMapper
) : BasePresenter(dispatchers) {

    private val tags = mutableListOf<TagItemViewModel>()

    private var currentSearchJob: Job? = null

    fun loadInitialData() {
        val recommendation = VideoRecommendationViewModel(
            title = "Test Title",
            channelName = "Test Channel Name",
            detailText = "Test Detail Text",
            videoInfo = VideoInfo(
                videoId = "",
                channelId = "",
                videoUri = "",
                providerUri = ""
            ),
            videoLength = "1:00"
        )

        val channel = ChannelListItemViewModel(
            channelId = "",
            providerUri = "",
            title = "Channel Title with Long Name",
            description = "Channel Description",
            channelImageUri = "",
            isSubscribed = false
        )

        val list = listOf(
            SectionHeaderViewModel(header = "Results"),
            recommendation,
            recommendation,
            channel,
            recommendation,
            channel,
            channel
        )

        flowOf(list)
            .calculateAndDispatchDiff(itemHandler = adapterItemHandler)
            .catch { Logger.logError(message = "Error loading Search Results.") }
            .launchIn(this)

        tagSuggestionRepository.getSuggestions()
            .mapEachItemWith(tagItemMapper)
            .flowOn(dispatchers.io)
            .onEach {
                tags.clear()
                tags.addAll(it)
                view.updateTags(tags)
            }
            .flowOn(dispatchers.main)
            .catch {
                Logger.logError(
                    throwable = it,
                    message = "Error retrieving tag suggestions."
                )
            }
            .launchIn(this)
    }

    fun handleTagItemSelected(tag: TagItemViewModel) {
        val index = tags.indexOf(tag)

        tags.removeAt(index)
        tags.add(index, tag.copy(isSelected = !tag.isSelected))

        view.updateTags(tags)
    }

    fun handleQuery(query: String) {
        currentSearchJob?.cancel()

        currentSearchJob = searchItemRepository.search(query = query)
            .mapEachItemWith(searchItemMapper)
            .calculateAndDispatchDiff(adapterItemHandler)
            .catch {
                Logger.logError(
                    throwable = it,
                    message = "Error retrieving search results for query = $query"
                )
            }
            .launchIn(this)
    }
}