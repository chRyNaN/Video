package com.chrynan.video.database.mapper

import com.chrynan.common.mapper.Mapper
import com.chrynan.common.model.Service
import com.chrynan.video.database.model.DbService
import javax.inject.Inject

class DbServiceFromServiceMapper @Inject constructor() : Mapper<Service, DbService> {

    override suspend fun map(model: Service): DbService =
        DbService(
            providerUri = model.providerUri,
            token = model.token,
            apiVersion = model.apiVersion
        )
}