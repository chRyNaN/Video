package com.chrynan.video.presentation.presenter

import com.chrynan.video.common.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.common.repository.ServiceProviderRepository
import com.chrynan.video.presentation.reducer.ServiceProviderListReducer
import com.chrynan.video.presentation.state.ServiceProviderListChange
import com.chrynan.video.presentation.state.ServiceProviderListIntent
import com.chrynan.video.presentation.state.ServiceProviderListState
import com.chrynan.video.presentation.view.View

class ServiceProviderListPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<ServiceProviderListIntent, ServiceProviderListState>,
    override val reducer: ServiceProviderListReducer,
    private val serviceProviderRepository: ServiceProviderRepository
) : BasePresenter<ServiceProviderListIntent, ServiceProviderListState, ServiceProviderListChange>(
    initialState = ServiceProviderListState.Initial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}