package com.chrynan.video.presentation.state

sealed class VideoPlayerState : State {

    object Initial : VideoPlayerState()
}

sealed class VideoPlayerIntent : Intent {


}

sealed class VideoPlayerChange : Change {


}