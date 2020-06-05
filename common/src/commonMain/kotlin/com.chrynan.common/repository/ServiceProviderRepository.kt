package com.chrynan.common.repository

import com.chrynan.common.model.ServiceProvider
import com.chrynan.common.model.core.UriString
import kotlinx.coroutines.flow.Flow

interface ServiceProviderRepository {

    fun getAll(): Flow<List<ServiceProvider>>

    suspend fun getByProviderUri(providerUri: UriString): ServiceProvider?

    suspend fun insert(model: ServiceProvider)

    suspend fun update(model: ServiceProvider)

    suspend fun deleteByProviderUri(providerUri: UriString)

    suspend fun deleteAll()
}