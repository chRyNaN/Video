package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.core.Change
import com.chrynan.video.presentation.core.Intent
import com.chrynan.video.presentation.core.State

sealed class OpenVideoDetailsState : State {

    object Initial : OpenVideoDetailsState()
}

sealed class OpenVideoDetailsIntent : Intent {


}

sealed class OpenVideoDetailsChange : Change {


}