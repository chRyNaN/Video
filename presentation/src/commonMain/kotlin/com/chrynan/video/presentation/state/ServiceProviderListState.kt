package com.chrynan.video.presentation.state

sealed class ServiceProviderListState : State {

    object Initial : ServiceProviderListState()
}

sealed class ServiceProviderListIntent : Intent {


}

sealed class ServiceProviderListChange : Change {


}
