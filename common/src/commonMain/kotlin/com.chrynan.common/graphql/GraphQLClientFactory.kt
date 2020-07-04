package com.chrynan.common.graphql

import com.chrynan.common.model.core.UriString

interface GraphQLClientFactory {

    fun create(baseUri: UriString): GraphQLClient
}