package com.chrynan.video.common.repository.database

import com.chrynan.video.common.model.ServiceProvider
import com.chrynan.video.common.model.core.UriString

interface ServiceProviderDatabaseRepository {

    suspend fun getAll(): List<ServiceProvider>

    suspend fun getByProviderUri(providerUri: UriString): ServiceProvider?

    suspend fun insert(model: ServiceProvider)

    suspend fun update(model: ServiceProvider)

    suspend fun deleteByProviderUri(providerUri: UriString)

    suspend fun deleteAll()
}