package com.chrynan.video.presentation.presenter

import com.chrynan.video.common.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.common.repository.ChannelRepository
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.reducer.ChannelDetailsReducer
import com.chrynan.video.presentation.state.ChannelDetailsChange
import com.chrynan.video.presentation.state.ChannelDetailsIntent
import com.chrynan.video.presentation.state.ChannelDetailsState

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