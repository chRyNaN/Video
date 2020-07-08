package com.chrynan.video.player

enum class MediaType(val typeName: String) {

    PROGRESSIVE(typeName = "progressive"),
    DASH(typeName = "dash"),
    SMOOTH_STREAMING(typeName = "smooth_streaming"),
    HLS(typeName = "hls");

    companion object {

        val DEFAULT = PROGRESSIVE

        fun fromTypeName(name: String): MediaType? =
            values().firstOrNull { it.typeName.toLowerCase() == name.toLowerCase() }
                ?: SMOOTH_STREAMING.takeIf { name.toLowerCase() == "smoothstreaming" }
    }
}