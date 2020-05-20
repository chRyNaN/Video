package com.chrynan.common.model

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Node

data class Reaction(
    override val id: ID,
    val created: String,
    val lastUpdated: String,
    val video: Video,
    val user: User,
    val reaction: String,
    val type: String? = null
) : Node