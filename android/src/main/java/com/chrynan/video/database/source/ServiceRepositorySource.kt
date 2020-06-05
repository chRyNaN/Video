package com.chrynan.video.database.source

import com.chrynan.common.model.Service
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
}