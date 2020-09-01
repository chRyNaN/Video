package com.chrynan.video.presentation.reducer

import com.chrynan.inject.Inject
import com.chrynan.video.presentation.core.Reducer
import com.chrynan.video.presentation.state.VideoPlayerChange
import com.chrynan.video.presentation.state.VideoPlayerState

class VideoPlayerReducer @Inject constructor() : Reducer<VideoPlayerState, VideoPlayerChange> {

    override suspend fun reduce(
        previous: VideoPlayerState,
        change: VideoPlayerChange
    ): VideoPlayerState {
        TODO("Not yet implemented")
    }
}