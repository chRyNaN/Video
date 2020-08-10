package com.chrynan.video.presentation.reducer

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.state.ServiceProviderDetailsChange
import com.chrynan.video.presentation.state.ServiceProviderDetailsState

class ServiceProviderDetailsReducer @Inject constructor(): Reducer<ServiceProviderDetailsState, ServiceProviderDetailsChange> {

    override suspend fun reduce(
        previous: ServiceProviderDetailsState,
        change: ServiceProviderDetailsChange
    ): ServiceProviderDetailsState {
        TODO("Not yet implemented")
    }
}