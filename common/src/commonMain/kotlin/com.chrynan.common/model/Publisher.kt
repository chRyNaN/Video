package com.chrynan.common.model

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Node
import com.chrynan.common.model.core.UriString

data class Publisher(
    override val id: ID,
    val created: String,
    val lastUpdated: String,
    val name: String,
    val description: String,
    val details: String? = null,
    val website: UriString? = null,
    val imageUri: UriString? = null
) : Node