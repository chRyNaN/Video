package com.chrynan.common.model.graphql

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GraphQLError(@SerialName(value = "message") val message: String? = null)