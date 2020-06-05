package com.chrynan.video.mapper.provider

import com.chrynan.common.mapper.Mapper
import com.chrynan.common.model.ServiceProvider
import com.chrynan.video.viewmodel.ServiceProviderListItemViewModel
import javax.inject.Inject

class ServiceProviderMapper @Inject constructor() :
    Mapper<ServiceProvider, ServiceProviderListItemViewModel> {

    override suspend fun map(model: ServiceProvider): ServiceProviderListItemViewModel =
        ServiceProviderListItemViewModel(
            uri = model.providerUri,
            name = model.name,
            imageUri = model.imageUri
        )
}