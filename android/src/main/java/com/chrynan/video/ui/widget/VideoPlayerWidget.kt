package com.chrynan.video.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import coil.ImageLoader
import com.chrynan.video.player.MediaPlayerView
import com.chrynan.video.player.widget.BaseVideoPlayerWidget
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView

class VideoPlayerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseVideoPlayerWidget(context, attrs, defStyleAttr),
    MediaPlayerView {

    init {
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
    }

    override val imageLoader: ImageLoader
        get() = TODO("Not yet implemented")

    override val previewImageView: ImageView?
        get() = TODO("Not yet implemented")

    val changeOrientationListeners = mutableSetOf<ChangeOrientationListener>()

    // TODO update this to work correctly
    private var orientation = Orientation.PORTRAIT

    override val widget: PlayerView
        get() = TODO("Not yet implemented")

    override fun setPreviewImage(drawable: Drawable?) {
        TODO("Not yet implemented")
    }

    override fun setPreviewImage(uri: String?) {
        TODO("Not yet implemented")
    }

    override fun togglePreviewImageVisibility(isVisible: Boolean) {
        TODO("Not yet implemented")
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