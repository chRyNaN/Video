package com.chrynan.video.database.source

import com.chrynan.video.common.model.ServiceProvider
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.common.repository.database.ServiceProviderDatabaseRepository
import com.chrynan.video.database.dao.DbServiceProviderDao
import com.chrynan.video.database.mapper.DbServiceFromServiceMapper
import com.chrynan.video.database.mapper.DbServiceToServiceMapper
import javax.inject.Inject

class ServiceProviderDatabaseSource @Inject constructor(
    private val dao: DbServiceProviderDao,
    private val toDbServiceMapper: DbServiceFromServiceMapper,
    private val toServiceMapper: DbServiceToServiceMapper
) : ServiceProviderDatabaseRepository {

    override suspend fun getAll(): List<ServiceProvider> = dao.getAll().map { toServiceMapper.map(it) }

    override suspend fun getByProviderUri(providerUri: UriString): ServiceProvider? =
        dao.getByProviderUri(providerUri)?.let { toServiceMapper.map(it) }

    override suspend fun insert(model: ServiceProvider) {
        val dbModel = toDbServiceMapper.map(model)

        dao.insert(dbModel)
    }

    override suspend fun update(model: ServiceProvider) {
        val dbModel = toDbServiceMapper.map(model)

        dao.update(dbModel)
    }

    override suspend fun deleteByProviderUri(providerUri: UriString) =
        dao.deleteByProviderUri(providerUri)

    override suspend fun deleteAll() = dao.deleteAll()
}