package com.chrynan.video.presentation.presenter

import com.chrynan.video.common.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.common.repository.SearchItemRepository
import com.chrynan.video.common.repository.TagSuggestionRepository
import com.chrynan.video.common.validation.validator.SearchQueryValidator
import com.chrynan.video.presentation.reducer.SearchReducer
import com.chrynan.video.presentation.state.SearchChange
import com.chrynan.video.presentation.state.SearchIntent
import com.chrynan.video.presentation.state.SearchState
import com.chrynan.video.presentation.view.View

class SearchPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<SearchIntent, SearchState>,
    override val reducer: SearchReducer,
    private val searchItemRepository: SearchItemRepository,
    private val tagSuggestionRepository: TagSuggestionRepository,
    private val searchQueryValidator: SearchQueryValidator
) : BasePresenter<SearchIntent, SearchState, SearchChange>(
    initialState = SearchState.Initial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}