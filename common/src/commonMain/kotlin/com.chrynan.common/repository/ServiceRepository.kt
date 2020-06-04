package com.chrynan.common.repository

import com.chrynan.common.model.Service

interface ServiceRepository {

    suspend fun getAll(): List<Service>
}