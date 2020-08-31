package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.core.Change
import com.chrynan.video.presentation.core.Intent
import com.chrynan.video.presentation.core.State

sealed class LbryVideoDetailsState : State {

    object Initial : LbryVideoDetailsState()
}

sealed class LbryVideoDetailsIntent : Intent {


}

sealed class LbryVideoDetailsChange : Change {


}