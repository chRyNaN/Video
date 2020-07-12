package com.chrynan.common.repository

import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    fun getVideoDetails(): Flow<VideoDetailsQuery.Data>
}