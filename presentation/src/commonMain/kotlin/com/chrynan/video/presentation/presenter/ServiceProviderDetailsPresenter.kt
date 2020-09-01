package com.chrynan.video.presentation.presenter

import com.chrynan.inject.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.reducer.ServiceProviderDetailsReducer
import com.chrynan.video.presentation.state.ServiceProviderDetailsChange
import com.chrynan.video.presentation.state.ServiceProviderDetailsIntent
import com.chrynan.video.presentation.state.ServiceProviderDetailsState

class ServiceProviderDetailsPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<ServiceProviderDetailsIntent, ServiceProviderDetailsState>,
    override val reducer: ServiceProviderDetailsReducer
) : BasePresenter<ServiceProviderDetailsIntent, ServiceProviderDetailsState, ServiceProviderDetailsChange>(
    initialState = ServiceProviderDetailsState.Initial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}