package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.core.Change
import com.chrynan.video.presentation.core.Intent
import com.chrynan.video.presentation.core.State

sealed class ServiceProviderListState : State {

    object Initial : ServiceProviderListState()
}

sealed class ServiceProviderListIntent : Intent {


}

sealed class ServiceProviderListChange : Change {


}
