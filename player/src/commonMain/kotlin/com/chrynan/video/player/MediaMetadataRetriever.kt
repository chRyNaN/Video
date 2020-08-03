package com.chrynan.video.player

interface MediaMetadataRetriever {

    suspend fun retrieve(uri: String): MediaMetadata
}