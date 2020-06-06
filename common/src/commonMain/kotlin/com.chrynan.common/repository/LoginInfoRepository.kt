package com.chrynan.common.repository

import com.chrynan.common.model.core.UriString
import com.chrynan.common.model.response.LoginInfoResponse

interface LoginInfoRepository {

    suspend fun getLoginInfoByProviderUri(providerUri: UriString): LoginInfoResponse?
}