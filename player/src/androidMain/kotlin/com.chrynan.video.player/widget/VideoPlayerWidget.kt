package com.chrynan.video.player.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import coil.ImageLoader
import coil.request.LoadRequest
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

    abstract val imageLoader: ImageLoader

    abstract val previewImageView: ImageView?

    override val widget: PlayerView
        get() = this

    override fun setPreviewImage(drawable: Drawable?) {
        previewImageView?.let {
            val request = LoadRequest.Builder(context)
                .data(drawable)
                .target(it)
                .build()

            imageLoader.execute(request)
        }
    }

    override fun setPreviewImage(uri: String?) {
        previewImageView?.let {
            val request = LoadRequest.Builder(context)
                .data(uri)
                .target(it)
                .build()

            imageLoader.execute(request)
        }
    }

    override fun togglePreviewImageVisibility(isVisible: Boolean) {
        previewImageView?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}