package com.chrynan.video.presentation.reducer

import com.chrynan.inject.Inject
import com.chrynan.video.presentation.core.Reducer
import com.chrynan.video.presentation.state.ServiceProviderListChange
import com.chrynan.video.presentation.state.ServiceProviderListState

class ServiceProviderListReducer @Inject constructor() :
    Reducer<ServiceProviderListState, ServiceProviderListChange> {

    override suspend fun reduce(
        previous: ServiceProviderListState,
        change: ServiceProviderListChange
    ): ServiceProviderListState {
        TODO("Not yet implemented")
    }
}