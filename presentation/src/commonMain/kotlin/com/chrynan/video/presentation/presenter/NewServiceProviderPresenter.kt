package com.chrynan.video.presentation.presenter

import com.chrynan.video.common.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.common.repository.LoginInfoRepository
import com.chrynan.video.common.repository.ServiceProviderRepository
import com.chrynan.video.common.validation.validator.UriStringValidator
import com.chrynan.video.presentation.reducer.NewServiceProviderReducer
import com.chrynan.video.presentation.state.NewServiceProviderChange
import com.chrynan.video.presentation.state.NewServiceProviderIntent
import com.chrynan.video.presentation.state.NewServiceProviderState
import com.chrynan.video.presentation.view.View

class NewServiceProviderPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<NewServiceProviderIntent, NewServiceProviderState>,
    override val reducer: NewServiceProviderReducer,
    private val validator: UriStringValidator,
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