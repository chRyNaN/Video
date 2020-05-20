package com.chrynan.common.model

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Node

data class Note(
    override val id: ID,
    val created: String,
    val lastUpdated: String,
    val content: String
) : Node