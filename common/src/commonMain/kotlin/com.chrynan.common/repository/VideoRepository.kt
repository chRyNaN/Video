package com.chrynan.common.repository

import com.chrynan.common.model.api.Video
import com.chrynan.common.model.api.VideoConnection
import com.chrynan.common.model.api.VideoEdge
import com.chrynan.common.repository.core.PaginatedRepository

interface VideoRepository : PaginatedRepository<Video, VideoEdge, VideoConnection>