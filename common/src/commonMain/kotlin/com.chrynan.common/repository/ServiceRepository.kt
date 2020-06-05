package com.chrynan.common.repository

import com.chrynan.common.model.Service
import com.chrynan.common.model.core.UriString

interface ServiceRepository {

    suspend fun getAll(): List<Service>

    suspend fun getByProviderUri(providerUri: UriString): Service?

    suspend fun insert(model: Service)

    suspend fun update(model: Service)

    suspend fun deleteByProviderUri(providerUri: UriString)

    suspend fun deleteAll()
}