package com.chrynan.video.database.mapper

import com.chrynan.common.mapper.Mapper
import com.chrynan.common.model.Service
import com.chrynan.video.database.model.DbService
import javax.inject.Inject

class DbServiceToServiceMapper @Inject constructor() : Mapper<DbService, Service> {

    override suspend fun map(model: DbService): Service =
        Service(
            providerUri = model.providerUri,
            token = model.token,
            apiVersion = model.apiVersion
        )
}