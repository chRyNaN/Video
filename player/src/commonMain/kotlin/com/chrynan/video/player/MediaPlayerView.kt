package com.chrynan.video.player

expect interface MediaPlayerView {

    fun setPreviewImage(uri: String?)

    fun togglePreviewImageVisibility(isVisible: Boolean)
}