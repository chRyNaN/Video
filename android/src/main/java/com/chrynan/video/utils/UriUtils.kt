package com.chrynan.video.utils

import android.media.MediaMetadataRetriever
import android.net.Uri
import com.chrynan.video.common.model.core.UriString

fun Uri.asUriString(): UriString = toString()

fun UriString.asAndroidUri(): Uri = Uri.parse(this)

fun UriString.decodeToAndroidUri(): Uri = Uri.parse(Uri.decode(this))

fun Uri.getFullMimeType(context: ActivityContext): String? {
    var type = context.contentResolver.getType(this)

    if (type == null) {
        val retriever = MediaMetadataRetriever().apply {
            setDataSource(context, this@getFullMimeType)
        }

        type = try {
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE)
        } catch (throwable: Throwable) {
            null
        }
    }

    if (type.isNullOrBlank()) {
        type = null
    }

    return type
}