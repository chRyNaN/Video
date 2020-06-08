package com.chrynan.common.utils

import com.chrynan.common.model.core.UriString
import com.chrynan.common.model.graphql.GraphQLRequest
import com.chrynan.common.model.graphql.GraphQLResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.post

const val API_ENDPOINT = "videoql"
const val AUTH_HEADER_NAME = "Authorization"
const val DEFAULT_URI_PROTOCOL = "https"

suspend inline fun <reified T> HttpClient.graphQLRequest(
    providerUri: UriString,
    token: String? = null,
    request: GraphQLRequest,
    block: HttpRequestBuilder.() -> Unit = {}
) = post<GraphQLResponse<T>>(getApiEndPoint(providerUri = providerUri)) {
    block()

    if (token != null) {
        header(AUTH_HEADER_NAME, token)
    }

    body = defaultSerializer().write(request)
}

fun getApiEndPoint(providerUri: UriString): UriString {
    val colonIndex = providerUri.indexOf(':')
    val hasProtocol = colonIndex != -1

    return if (hasProtocol) {
        "$providerUri/${API_ENDPOINT}"
    } else {
        "$DEFAULT_URI_PROTOCOL://$providerUri/${API_ENDPOINT}"
    }
}