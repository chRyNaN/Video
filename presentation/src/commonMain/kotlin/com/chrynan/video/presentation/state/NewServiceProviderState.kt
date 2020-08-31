package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.core.Change
import com.chrynan.video.presentation.core.Intent
import com.chrynan.video.presentation.core.State

sealed class NewServiceProviderState : State {

    object Initial : NewServiceProviderState()
}

sealed class NewServiceProviderIntent : Intent {


}

sealed class NewServiceProviderChange : Change {


}