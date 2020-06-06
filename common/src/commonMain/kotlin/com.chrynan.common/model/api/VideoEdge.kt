package com.chrynan.common.model.api

import com.chrynan.common.model.core.Cursor
import com.chrynan.common.model.core.Edge
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoEdge(
    @SerialName(value = "cursor") override val cursor: Cursor,
    @SerialName(value = "node") override val node: Video
) : Edge<Video>