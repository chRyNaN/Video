package com.chrynan.video.presentation.state

sealed class SettingsState : State {

    object Initial : SettingsState()
}

sealed class SettingsIntent : Intent {


}

sealed class SettingsChange : Change {


}