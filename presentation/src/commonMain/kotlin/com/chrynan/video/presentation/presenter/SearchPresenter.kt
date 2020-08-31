package com.chrynan.video.presentation.presenter

import com.chrynan.logger.Logger
import com.chrynan.video.common.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.action.search.SearchClearAction
import com.chrynan.video.presentation.action.search.SearchLoadMoreAction
import com.chrynan.video.presentation.action.search.SearchQueryAction
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.core.perform
import com.chrynan.video.presentation.reducer.SearchReducer
import com.chrynan.video.presentation.state.SearchChange
import com.chrynan.video.presentation.state.SearchIntent
import com.chrynan.video.presentation.state.SearchState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn

class SearchPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<SearchIntent, SearchState>,
    override val reducer: SearchReducer,
    private val searchQueryAction: SearchQueryAction,
    private val searchLoadMoreAction: SearchLoadMoreAction,
    private val searchClearAction: SearchClearAction
) : BasePresenter<SearchIntent, SearchState, SearchChange>(
    initialState = SearchState.DisplayingNoInput,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        view.intents()
            .flowOn(dispatchers.main)
            .perform {
                when (it) {
                    is SearchIntent.Clear -> searchClearAction(it)
                    is SearchIntent.Search -> searchQueryAction(it)
                    is SearchIntent.LoadMore -> searchLoadMoreAction(it)
                }
            }
            .reduceAndRender()
            .catch {
                Logger.logError(
                    throwable = it,
                    message = "Error listening to intents in SearchPresenter."
                )
            }
            .launchIn(this)
    }
}