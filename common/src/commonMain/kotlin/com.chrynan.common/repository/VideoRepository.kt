package com.chrynan.common.repository

import com.chrynan.common.model.Video
import com.chrynan.common.model.VideoConnection
import com.chrynan.common.model.VideoEdge
import com.chrynan.common.repository.core.PaginatedRepository

interface VideoRepository : PaginatedRepository<Video, VideoEdge, VideoConnection>