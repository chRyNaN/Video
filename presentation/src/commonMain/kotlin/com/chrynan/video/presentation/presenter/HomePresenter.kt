package com.chrynan.video.presentation.presenter

import com.chrynan.logger.Logger
import com.chrynan.video.common.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.action.home.HomeLoadInitialAction
import com.chrynan.video.presentation.action.home.HomeLoadMoreAction
import com.chrynan.video.presentation.action.home.HomeRefreshAction
import com.chrynan.video.presentation.action.perform
import com.chrynan.video.presentation.reducer.HomeReducer
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent
import com.chrynan.video.presentation.state.HomeState
import com.chrynan.video.presentation.view.View
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn

class HomePresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<HomeIntent, HomeState>,
    override val reducer: HomeReducer,
    private val loadInitialAction: HomeLoadInitialAction,
    private val loadMoreAction: HomeLoadMoreAction,
    private val refreshAction: HomeRefreshAction
) : BasePresenter<HomeIntent, HomeState, HomeChange>(
    initialState = HomeState.LoadingInitial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        view.intents()
            .flowOn(dispatchers.io)
            .perform {
                when (it) {
                    is HomeIntent.LoadInitial -> loadInitialAction(it)
                    is HomeIntent.Refresh -> refreshAction(it)
                    is HomeIntent.LoadMore -> loadMoreAction(it)
                }
            }
            .reduceAndRender()
            .catch {
                Logger.logError(
                    throwable = it,
                    message = "Error listening to intents in HomePresenter."
                )
            }
            .launchIn(this)
    }
}