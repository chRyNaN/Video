package com.chrynan.video.presentation.presenter

import com.chrynan.video.common.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.common.provider.OpenVideoProvider
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.reducer.VideoPlayerReducer
import com.chrynan.video.presentation.state.VideoPlayerChange
import com.chrynan.video.presentation.state.VideoPlayerIntent
import com.chrynan.video.presentation.state.VideoPlayerState

class VideoPlayerPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<VideoPlayerIntent, VideoPlayerState>,
    override val reducer: VideoPlayerReducer,
    private val openVideoProvider: OpenVideoProvider
) : BasePresenter<VideoPlayerIntent, VideoPlayerState, VideoPlayerChange>(
    initialState = VideoPlayerState.Initial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}