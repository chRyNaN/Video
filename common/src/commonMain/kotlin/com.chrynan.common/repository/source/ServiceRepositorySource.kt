package com.chrynan.common.repository.source

import com.chrynan.common.Inject
import com.chrynan.common.model.Service
import com.chrynan.common.repository.ServiceRepository

class ServiceRepositorySource @Inject constructor() : ServiceRepository {

    override suspend fun getAll(): List<Service> = emptyList()
}