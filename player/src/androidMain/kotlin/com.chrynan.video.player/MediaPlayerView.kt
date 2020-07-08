package com.chrynan.video.player

import android.graphics.drawable.Drawable
import com.google.android.exoplayer2.ui.PlayerView

actual interface MediaPlayerView {

    val widget: PlayerView

    fun setPreviewImage(drawable: Drawable?)

    actual fun setPreviewImage(uri: String?)

    actual fun togglePreviewImageVisibility(isVisible: Boolean)
}