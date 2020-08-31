package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.core.Change
import com.chrynan.video.presentation.core.Intent
import com.chrynan.video.presentation.core.State

sealed class ServiceProviderDetailsState : State {

    object Initial : ServiceProviderDetailsState()
}

sealed class ServiceProviderDetailsIntent : Intent {


}

sealed class ServiceProviderDetailsChange : Change {


}