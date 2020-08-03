package com.chrynan.video.player

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import android.media.MediaMetadataRetriever as AndroidRetriever

class AndroidMediaMetadataRetriever(private val context: Context) : MediaMetadataRetriever {

    override suspend fun retrieve(uri: String): MediaMetadata {
        val androidUri = Uri.parse(uri)

        val retriever = AndroidRetriever().apply {
            setDataSource(context, androidUri)
        }

        return MediaMetadata(
            uri = uri,
            title = retriever.title,
            author = retriever.author,
            date = retriever.date,
            duration = retriever.duration,
            mimeType = androidUri.getFullMimeType(context, retriever),
            frameCount = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) retriever.frameCount else null,
            height = retriever.height,
            width = retriever.width,
            rotation = retriever.rotation
        )
    }

    private val AndroidRetriever.title: String?
        get() = getValueOrNull(AndroidRetriever.METADATA_KEY_TITLE)

    private val AndroidRetriever.author: String?
        get() = getValueOrNull(AndroidRetriever.METADATA_KEY_AUTHOR) ?: extractMetadata(
            AndroidRetriever.METADATA_KEY_ARTIST
        )

    private val AndroidRetriever.date: String?
        get() = getValueOrNull(AndroidRetriever.METADATA_KEY_DATE)

    private val AndroidRetriever.duration: Long?
        get() = try {
            getValueOrNull(AndroidRetriever.METADATA_KEY_DURATION)?.toLong()
        } catch (throwable: Throwable) {
            null
        }

    private val AndroidRetriever.frameCount: Int?
        @RequiresApi(Build.VERSION_CODES.P)
        get() = try {
            getValueOrNull(AndroidRetriever.METADATA_KEY_VIDEO_FRAME_COUNT)?.toInt()
        } catch (throwable: Throwable) {
            null
        }

    private val AndroidRetriever.height: Int?
        get() = try {
            getValueOrNull(AndroidRetriever.METADATA_KEY_VIDEO_HEIGHT)?.toInt()
        } catch (throwable: Throwable) {
            null
        }

    private val AndroidRetriever.width: Int?
        get() = try {
            getValueOrNull(AndroidRetriever.METADATA_KEY_VIDEO_WIDTH)?.toInt()
        } catch (throwable: Throwable) {
            null
        }

    private val AndroidRetriever.rotation: Float?
        get() = try {
            getValueOrNull(AndroidRetriever.METADATA_KEY_VIDEO_ROTATION)?.toFloat()
        } catch (throwable: Throwable) {
            null
        }

    private fun Uri.getFullMimeType(context: Context, retriever: AndroidRetriever): String? {
        var type = context.contentResolver.getType(this)

        if (type == null) {
            type = try {
                retriever.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_MIMETYPE)
            } catch (throwable: Throwable) {
                null
            }
        }

        if (type.isNullOrBlank()) {
            type = null
        }

        return type
    }

    private fun AndroidRetriever.getValueOrNull(key: Int): String? =
        try {
            extractMetadata(key)
        } catch (throwable: Throwable) {
            null
        }
}