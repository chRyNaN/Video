package com.chrynan.video.presentation.state

sealed class LbryVideoDetailsState : State {

    object Initial : LbryVideoDetailsState()
}

sealed class LbryVideoDetailsIntent : Intent {


}

sealed class LbryVideoDetailsChange : Change {


}