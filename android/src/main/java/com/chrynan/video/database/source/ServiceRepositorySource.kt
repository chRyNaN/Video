package com.chrynan.video.database.source

import com.chrynan.common.model.Service
import com.chrynan.common.model.core.UriString
import com.chrynan.common.repository.ServiceRepository
import com.chrynan.video.database.dao.DbServiceDao
import com.chrynan.video.database.mapper.DbServiceFromServiceMapper
import com.chrynan.video.database.mapper.DbServiceToServiceMapper
import javax.inject.Inject

class ServiceRepositorySource @Inject constructor(
    private val dao: DbServiceDao,
    private val toDbServiceMapper: DbServiceFromServiceMapper,
    private val toServiceMapper: DbServiceToServiceMapper
) : ServiceRepository {

    override suspend fun getAll(): List<Service> = dao.getAll().map { toServiceMapper.map(it) }

    override suspend fun getByProviderUri(providerUri: UriString): Service? =
        dao.getByProviderUri(providerUri)?.let { toServiceMapper.map(it) }

    override suspend fun insert(model: Service) {
        val dbModel = toDbServiceMapper.map(model)

        dao.insert(dbModel)
    }

    override suspend fun update(model: Service) {
        val dbModel = toDbServiceMapper.map(model)

        dao.update(dbModel)
    }

    override suspend fun deleteByProviderUri(providerUri: UriString) =
        dao.deleteByProviderUri(providerUri)

    override suspend fun deleteAll() = dao.deleteAll()
}