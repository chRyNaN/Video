package com.chrynan.common.model.api

import com.chrynan.common.model.core.*

data class Video(
    override val id: ID,
    override val created: Moment,
    override val lastUpdated: Moment,
    val published: Moment,
    val uri: UriString,
    val name: String,
    val description: String? = null,
    val about: String? = null,
    val previewImage: UriString? = null,
    val category: String? = null,
    val tags: List<String> = emptyList(),
    val viewCount: Long? = null,
    val isLive: Boolean = false,
    val lengthInMilliseconds: Long,
    val streamType: VideoStreamType = VideoStreamType.PROGRESSIVE,
    val standaloneSubtitles: List<Subtitle> = emptyList(),
    val drmInfo: DrmInfo,
    val actions: List<VideoAction> = emptyList()
) : Node,
    TimeDetail