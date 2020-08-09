package com.chrynan.video.presentation.state

sealed class ChannelDetailsState : State {

    object Initial : ChannelDetailsState()
}

sealed class ChannelDetailsIntent : Intent {

}

sealed class ChannelDetailsChange : Change {

}