package com.chrynan.video.presentation.state

sealed class HomeState : State {

    object Initial : HomeState()
}

sealed class HomeIntent : Intent {


}

sealed class HomeChange : Change {


}