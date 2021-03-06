package com.chrynan.video.presentation.mapper.provider

import com.chrynan.inject.Inject
import com.chrynan.video.common.model.ServiceProvider
import com.chrynan.video.presentation.core.Mapper
import com.chrynan.video.presentation.viewmodel.ServiceProviderListItemViewModel

class ServiceProviderMapper @Inject constructor() :
    Mapper<ServiceProvider, ServiceProviderListItemViewModel> {

    override suspend fun map(model: ServiceProvider): ServiceProviderListItemViewModel =
        ServiceProviderListItemViewModel(
            uri = model.providerUri,
            name = model.name,
            imageUri = model.imageUri
        )
}