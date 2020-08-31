package com.chrynan.video.presentation.presenter

import com.chrynan.video.common.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers

import com.chrynan.video.common.provider.OpenVideoProvider
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.reducer.OpenVideoDetailsReducer
import com.chrynan.video.presentation.state.OpenVideoDetailsChange
import com.chrynan.video.presentation.state.OpenVideoDetailsIntent
import com.chrynan.video.presentation.state.OpenVideoDetailsState

class OpenVideoDetailsPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<OpenVideoDetailsIntent, OpenVideoDetailsState>,
    override val reducer: OpenVideoDetailsReducer,
    private val openVideoProvider: OpenVideoProvider
) : BasePresenter<OpenVideoDetailsIntent, OpenVideoDetailsState, OpenVideoDetailsChange>(
    initialState = OpenVideoDetailsState.Initial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}