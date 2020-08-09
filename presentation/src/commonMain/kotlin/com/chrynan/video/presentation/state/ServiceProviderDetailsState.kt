package com.chrynan.video.presentation.state

sealed class ServiceProviderDetailsState : State {

    object Initial : ServiceProviderDetailsState()
}

sealed class ServiceProviderDetailsIntent : Intent {


}

sealed class ServiceProviderDetailsChange : Change {


}