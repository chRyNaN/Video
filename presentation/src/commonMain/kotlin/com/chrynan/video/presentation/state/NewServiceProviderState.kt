package com.chrynan.video.presentation.state

sealed class NewServiceProviderState : State {

    object Initial : NewServiceProviderState()
}

sealed class NewServiceProviderIntent : Intent {


}

sealed class NewServiceProviderChange : Change {


}