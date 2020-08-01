package com.chrynan.video.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import com.chrynan.video.player.MediaPlayerView
import com.chrynan.video.player.widget.BaseVideoPlayerWidget
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout

class VideoPlayerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseVideoPlayerWidget(context, attrs, defStyleAttr),
    MediaPlayerView {

    init {
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
    }

    override val previewImageView: ImageView?
        get() = null // TODO

    val changeOrientationListeners = mutableSetOf<ChangeOrientationListener>()

    // TODO update this to work correctly
    private var orientation = Orientation.PORTRAIT

    override fun setPreviewImage(drawable: Drawable?) {
        // TODO
    }

    override fun setPreviewImage(uri: String?) {
        // TODO
    }

    override fun togglePreviewImageVisibility(isVisible: Boolean) {
        // TODO
    }

    private fun Collection<ChangeOrientationListener>.notifyChangeOrientation(orientation: Orientation) =
        forEach { it.onChangeOrientation(orientation) }

    interface ChangeOrientationListener {

        fun onChangeOrientation(orientation: Orientation)
    }

    enum class Orientation {

        PORTRAIT,
        LANDSCAPE
    }
}