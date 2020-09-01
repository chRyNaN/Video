package com.chrynan.video.common.repository.source

import com.chrynan.inject.Inject
import com.chrynan.video.common.model.ServiceProvider
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.common.repository.ServiceProviderRepository
import com.chrynan.video.common.repository.database.ServiceProviderDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ServiceProviderSource @Inject constructor(private val databaseRepository: ServiceProviderDatabaseRepository) :
    ServiceProviderRepository {

    override fun getAll(): Flow<List<ServiceProvider>> = flow { emit(databaseRepository.getAll()) }

    override suspend fun getByProviderUri(providerUri: UriString): ServiceProvider? =
        databaseRepository.getByProviderUri(providerUri = providerUri)

    override suspend fun insert(model: ServiceProvider) = databaseRepository.insert(model = model)

    override suspend fun update(model: ServiceProvider) = databaseRepository.update(model = model)

    override suspend fun deleteByProviderUri(providerUri: UriString) =
        databaseRepository.deleteByProviderUri(providerUri = providerUri)

    override suspend fun deleteAll() = databaseRepository.deleteAll()
}