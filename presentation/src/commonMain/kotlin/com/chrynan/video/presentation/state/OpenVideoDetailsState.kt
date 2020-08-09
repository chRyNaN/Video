package com.chrynan.video.presentation.state

sealed class OpenVideoDetailsState : State {

    object Initial : OpenVideoDetailsState()
}

sealed class OpenVideoDetailsIntent : Intent {


}

sealed class OpenVideoDetailsChange : Change {


}