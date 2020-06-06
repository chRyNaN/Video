package com.chrynan.common.model.api

import com.chrynan.common.model.core.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    @SerialName(value = "id") override val id: ID,
    @SerialName(value = "created") override val created: Moment,
    @SerialName(value = "lastUpdated") override val lastUpdated: Moment,
    @SerialName(value = "published") val published: Moment,
    @SerialName(value = "uri") val uri: UriString,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "description") val description: String? = null,
    @SerialName(value = "about") val about: String? = null,
    @SerialName(value = "previewImage") val previewImage: UriString? = null,
    @SerialName(value = "category") val category: String? = null,
    @SerialName(value = "tags") val tags: List<String> = emptyList(),
    @SerialName(value = "viewCount") val viewCount: Long? = null,
    @SerialName(value = "isLive") val isLive: Boolean = false,
    @SerialName(value = "lengthInMilliseconds") val lengthInMilliseconds: Long,
    @SerialName(value = "streamType") val streamType: VideoStreamType = VideoStreamType.PROGRESSIVE,
    @SerialName(value = "standaloneSubtitles") val standaloneSubtitles: List<Subtitle> = emptyList(),
    @SerialName(value = "drmInfo") val drmInfo: DrmInfo,
    @SerialName(value = "actions") val actions: List<VideoAction> = emptyList()
) : Node,
    TimeDetail