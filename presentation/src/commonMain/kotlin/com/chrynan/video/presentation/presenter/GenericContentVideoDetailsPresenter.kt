package com.chrynan.video.presentation.presenter

import com.chrynan.video.common.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.reducer.GenericContentVideoDetailsReducer
import com.chrynan.video.presentation.state.GenericContentVideoDetailsChange
import com.chrynan.video.presentation.state.GenericContentVideoDetailsIntent
import com.chrynan.video.presentation.state.GenericContentVideoDetailsState

class GenericContentVideoDetailsPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<GenericContentVideoDetailsIntent, GenericContentVideoDetailsState>,
    override val reducer: GenericContentVideoDetailsReducer
) : BasePresenter<GenericContentVideoDetailsIntent, GenericContentVideoDetailsState, GenericContentVideoDetailsChange>(
    initialState = GenericContentVideoDetailsState.Initial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}