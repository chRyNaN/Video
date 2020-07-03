package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.model.SearchQuery
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.repository.SearchItemRepository
import com.chrynan.common.repository.TagSuggestionRepository
import com.chrynan.common.utils.flowFrom
import com.chrynan.common.utils.mapEachItemWith
import com.chrynan.common.validation.core.ValidationResult
import com.chrynan.common.validation.validator.SearchQueryValidator
import com.chrynan.logger.Logger
import com.chrynan.video.di.qualifier.SearchQualifier
import com.chrynan.video.mapper.TagItemMapper
import com.chrynan.video.viewmodel.TagItemViewModel
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import com.chrynan.video.ui.view.SearchView
import com.chrynan.video.utils.TagItemHandler
import com.chrynan.video.viewmodel.AdapterItem
import com.chrynan.video.viewmodel.ChannelListItemViewModel
import com.chrynan.video.viewmodel.SectionHeaderViewModel
import com.chrynan.video.viewmodel.VideoRecommendationViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val view: SearchView,
    @SearchQualifier.AdapterItemHandler private val adapterItemHandler: AdapterItemHandler<AdapterItem>,
    private val searchItemRepository: SearchItemRepository,
    private val tagSuggestionRepository: TagSuggestionRepository,
    private val tagItemMapper: TagItemMapper,
    private val tagItemHandler: TagItemHandler,
    private val searchQueryValidator: SearchQueryValidator
) : BasePresenter(dispatchers) {

    private var retrievedTags = emptyList<TagItemViewModel>()
    private var editedTags = emptyList<TagItemViewModel>()

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
                tagItemHandler.updateTags(it)

                view.updateTags(tagItemHandler.allTags)
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
        tagItemHandler.selectTag(tag)

        view.updateTags(tagItemHandler.allTags)
    }

    @OptIn(FlowPreview::class)
    fun handleQuery(query: String) {
        currentSearchJob?.cancel()
    }

    fun handleSubscribeButtonSelected(item: ChannelListItemViewModel, isChecked: Boolean) {
        // TODO
    }

    private fun getSearchQueryValidationFlow(query: String): Flow<ValidationResult<String>> =
        flowFrom {
            val searchQuery = SearchQuery(
                query = query,
                selectedTags = tagItemHandler.selectedTags.map { it.name }.toSet()
            )

            searchQueryValidator(searchQuery)
        }
}