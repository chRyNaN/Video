package com.chrynan.video.presentation.presenter

import com.chrynan.common.Inject
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.repository.FeedItemRepository
import com.chrynan.video.presentation.reducer.HomeReducer
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent
import com.chrynan.video.presentation.state.HomeState
import com.chrynan.video.presentation.view.View

class HomePresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<HomeIntent, HomeState>,
    override val reducer: HomeReducer,
    private val feedRepository: FeedItemRepository
) : BasePresenter<HomeIntent, HomeState, HomeChange>(
    initialState = HomeState.Initial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}