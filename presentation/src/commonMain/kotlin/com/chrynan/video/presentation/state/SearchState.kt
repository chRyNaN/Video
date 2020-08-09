package com.chrynan.video.presentation.state

sealed class SearchState : State {

    object Initial : SearchState()
}

sealed class SearchIntent : Intent {


}

sealed class SearchChange : Change {


}