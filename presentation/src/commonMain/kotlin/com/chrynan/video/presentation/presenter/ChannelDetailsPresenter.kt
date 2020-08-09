package com.chrynan.video.presentation.presenter

import com.chrynan.common.Inject
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.repository.ChannelRepository
import com.chrynan.video.presentation.reducer.ChannelDetailsReducer
import com.chrynan.video.presentation.state.ChannelDetailsChange
import com.chrynan.video.presentation.state.ChannelDetailsIntent
import com.chrynan.video.presentation.state.ChannelDetailsState
import com.chrynan.video.presentation.view.View

class ChannelDetailsPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<ChannelDetailsIntent, ChannelDetailsState>,
    override val reducer: ChannelDetailsReducer,
    private val channelRepository: ChannelRepository
) : BasePresenter<ChannelDetailsIntent, ChannelDetailsState, ChannelDetailsChange>(
    dispatchers = dispatchers,
    initialState = ChannelDetailsState.Initial
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}