package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.core.Change
import com.chrynan.video.presentation.core.Intent
import com.chrynan.video.presentation.core.State

sealed class SettingsState : State {

    object Initial : SettingsState()
}

sealed class SettingsIntent : Intent {


}

sealed class SettingsChange : Change {


}