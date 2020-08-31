package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.core.Change
import com.chrynan.video.presentation.core.Intent
import com.chrynan.video.presentation.core.State

sealed class ChannelDetailsState : State {

    object Initial : ChannelDetailsState()
}

sealed class ChannelDetailsIntent : Intent {

}

sealed class ChannelDetailsChange : Change {

}