package com.chrynan.common.model

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Node
import com.chrynan.common.model.core.UriString

data class VideoContributor(
    override val id: ID,
    val name: String,
    val position: String,
    val role: VideoCreditRole,
    val imageUri: UriString? = null
) : Node