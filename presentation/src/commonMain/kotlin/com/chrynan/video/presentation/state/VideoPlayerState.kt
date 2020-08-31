package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.core.Change
import com.chrynan.video.presentation.core.Intent
import com.chrynan.video.presentation.core.State

sealed class VideoPlayerState : State {

    object Initial : VideoPlayerState()
}

sealed class VideoPlayerIntent : Intent {


}

sealed class VideoPlayerChange : Change {


}