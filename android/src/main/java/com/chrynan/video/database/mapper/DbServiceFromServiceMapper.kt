package com.chrynan.video.database.mapper

import com.chrynan.video.common.mapper.Mapper
import com.chrynan.video.common.model.ServiceProvider
import com.chrynan.video.database.model.DbServiceProvider
import javax.inject.Inject

class DbServiceFromServiceMapper @Inject constructor() :
    Mapper<ServiceProvider, DbServiceProvider> {

    override suspend fun map(model: ServiceProvider): DbServiceProvider =
        DbServiceProvider(
            providerUri = model.providerUri,
            token = model.token,
            apiVersion = model.apiVersion,
            name = model.name,
            imageUri = model.imageUri
        )
}