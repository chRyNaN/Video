package com.chrynan.video.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.chrynan.common.model.core.UriString
import com.chrynan.video.media.MediaPlayerView
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView

class VideoPlayerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : PlayerView(context, attrs, defStyleAttr),
    MediaPlayerView {

    init {
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
    }

    val changeOrientationListeners = mutableSetOf<ChangeOrientationListener>()

    // TODO update this to work correctly
    private var orientation = Orientation.PORTRAIT

    override fun attachPlayer(player: Player) {
        this.player = player
        requestLayout()
    }

    override fun detachPlayer() {
        player = null
    }

    override fun showPreviewImage(previewImageUri: UriString?) {
        // TODO
    }

    override fun showVideo() {
        visibility = View.VISIBLE
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