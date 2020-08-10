package com.chrynan.video.common.graphql

import com.chrynan.video.common.model.core.UriString

interface GraphQLClientFactory {

    fun getOrCreate(baseUri: UriString): GraphQLClient
}