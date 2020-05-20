package com.chrynan.common.model

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Node

data class User(
    override val id: ID,
    val name: String,
    val email: String,
    val created: String,
    val lastUpdated: String,
    val username: String? = null,
    val phoneNumber: String? = null
): Node