package com.chrynan.video.presentation.presenter

import com.chrynan.video.common.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.reducer.LbryVideoDetailsReducer
import com.chrynan.video.presentation.state.LbryVideoDetailsChange
import com.chrynan.video.presentation.state.LbryVideoDetailsIntent
import com.chrynan.video.presentation.state.LbryVideoDetailsState
import com.chrynan.video.presentation.view.View

class LbryVideoDetailsPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<LbryVideoDetailsIntent, LbryVideoDetailsState>,
    override val reducer: LbryVideoDetailsReducer
) : BasePresenter<LbryVideoDetailsIntent, LbryVideoDetailsState, LbryVideoDetailsChange>(
    initialState = LbryVideoDetailsState.Initial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}