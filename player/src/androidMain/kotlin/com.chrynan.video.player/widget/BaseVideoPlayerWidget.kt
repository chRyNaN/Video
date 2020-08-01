package com.chrynan.video.player.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import coil.api.load
import com.chrynan.video.player.MediaPlayerView
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView

abstract class BaseVideoPlayerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : PlayerView(context, attrs, defStyleAttr),
    MediaPlayerView {

    init {
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
    }

    abstract val previewImageView: ImageView?

    override val widget: PlayerView
        get() = this

    override fun setPreviewImage(drawable: Drawable?) {
        previewImageView?.load(drawable)
    }

    override fun setPreviewImage(uri: String?) {
        previewImageView?.load(uri)
    }

    override fun togglePreviewImageVisibility(isVisible: Boolean) {
        previewImageView?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}