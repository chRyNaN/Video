package com.chrynan.common.repository.source

import com.chrynan.common.Inject
import com.chrynan.common.api.WebApi
import com.chrynan.common.model.core.UriString
import com.chrynan.common.model.graphql.GraphQLResponse
import com.chrynan.common.model.response.LoginInfoResponse
import com.chrynan.common.repository.LoginInfoRepository
import com.chrynan.logger.Logger

class LoginInfoRepositorySource @Inject constructor(private val webApi: WebApi) :
    LoginInfoRepository {

    override suspend fun getLoginInfoByProviderUri(providerUri: UriString): LoginInfoResponse? {
        val response: GraphQLResponse<LoginInfoResponse>? = try {
            webApi.getLoginInfo(providerUri = providerUri)
        } catch (throwable: Throwable) {
            Logger.logError(
                throwable = throwable,
                message = "Error retrieving LoginInfo for Provider with uri = $providerUri."
            )
            null
        }

        if (response?.isError == true) {
            Logger.logError(message = "Error retrieving LoginInfo for Provider with uri = $providerUri. Errors = ${response.errors}.")
        }

        return response?.data
    }
}