package com.chrynan.video.presentation.presenter

import com.chrynan.common.Inject
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.reducer.ServiceProviderDetailsReducer
import com.chrynan.video.presentation.state.ServiceProviderDetailsChange
import com.chrynan.video.presentation.state.ServiceProviderDetailsIntent
import com.chrynan.video.presentation.state.ServiceProviderDetailsState
import com.chrynan.video.presentation.view.View

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