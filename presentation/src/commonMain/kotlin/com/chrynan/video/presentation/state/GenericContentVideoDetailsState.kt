package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.core.Change
import com.chrynan.video.presentation.core.Intent
import com.chrynan.video.presentation.core.State

sealed class GenericContentVideoDetailsState : State {

    object Initial : GenericContentVideoDetailsState()
}

sealed class GenericContentVideoDetailsIntent : Intent {


}

sealed class GenericContentVideoDetailsChange : Change {


}