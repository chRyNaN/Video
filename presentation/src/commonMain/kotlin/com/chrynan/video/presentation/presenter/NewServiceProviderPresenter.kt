package com.chrynan.video.presentation.presenter

import com.chrynan.inject.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.common.repository.LoginInfoRepository
import com.chrynan.video.common.repository.ServiceProviderRepository
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.reducer.NewServiceProviderReducer
import com.chrynan.video.presentation.state.NewServiceProviderChange
import com.chrynan.video.presentation.state.NewServiceProviderIntent
import com.chrynan.video.presentation.state.NewServiceProviderState

class NewServiceProviderPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<NewServiceProviderIntent, NewServiceProviderState>,
    override val reducer: NewServiceProviderReducer,
    private val serviceProviderRepository: ServiceProviderRepository,
    private val loginInfoRepository: LoginInfoRepository
) : BasePresenter<NewServiceProviderIntent, NewServiceProviderState, NewServiceProviderChange>(
    initialState = NewServiceProviderState.Initial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}